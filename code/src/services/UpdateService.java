package services;

import models.TicketModel;
import repository.TicketRepository;

/**
 * Service class implementing the CRUDService interface, specifically focused on ticket update operations.
 * This class follows the Single Responsibility Principle by handling only update-related operations
 * in the Call Center Ticketing System.
 */
public class UpdateService implements CRUDService {

    /**
     * Repository instance for ticket data operations.
     * Handles the actual storage and retrieval of ticket data.
     */
    private TicketRepository repository;

    /**
     * Constructs an UpdateService with the specified ticket repository.
            * Uses dependency injection to maintain loose coupling between service and repository layers.
            *
            * @param repository The TicketRepository instance to be used for ticket operations
     */
    public UpdateService(TicketRepository repository) {
        this.repository = repository;
    }

    /**
     * Updates an existing ticket in the system.
     * Delegates the update operation to the repository layer.
     *
     * @param ticket The TicketModel object containing the updated ticket information
     */
    @Override
    public void update(TicketModel ticket) {
        repository.updateTicket(ticket);
    }

    // Unused methods from interface
    @Override public void create(TicketModel ticket) {}
    @Override public TicketModel read(int id) { return null; }
    @Override public boolean delete(int id) { return false; }
}