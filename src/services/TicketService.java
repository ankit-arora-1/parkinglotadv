package services;

import dtos.IssueTicketRequestDto;
import exceptions.GateNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.SpotAssignmentStrategyFactory;
import strategies.SpotAssignmnetStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(
            GateRepository gateRepository,
            VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository,
            TicketRepository ticketRepository
    ) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId) throws GateNotFoundException {
        // Create ticket object
        // get gate info from DB
        // Assign spot
        // return ticket

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(gateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }

        Gate gate = gateOptional.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getCurrentOperator());

        Optional<Vehicle> vehicleOptional = vehicleRepository
                .getVehicleByNumber(vehicleNumber);

        Vehicle savedVehicle;
        if(vehicleOptional.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = vehicleOptional.get();
        }

        ticket.setVehicle(savedVehicle);

        ParkingLot parkingLot = parkingLotRepository.getParkingLotFromGate(gate);
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot
                .getSpotAssignmentStrategyType();


        SpotAssignmnetStrategy spotAssignmnetStrategy =
                SpotAssignmentStrategyFactory.getSpotForType(spotAssignmentStrategyType);

        ticket.setAssignedSpot(
                spotAssignmnetStrategy.getSpot(gate, vehicleType)
        );

        ticket.setNumber("TICKET - " + ticket.getId());
        return ticketRepository.save(ticket);
    }
}
