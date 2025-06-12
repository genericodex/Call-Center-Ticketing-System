package services;

import models.TicketModel;
import repository.TicketRepository;


/**
 * Service class implementing the CRUDService interface, specifically focused on ticket deletion operations.
 * This class follows the Single Responsibility Principle by handling only delete-related operations
 * in the Call Center Ticketing System.
 */
public class DeleteService implements CRUDService {

    /**
     * Repository instance for ticket data operations.
     * Handles the actual storage and deletion of ticket data.
     */
    private TicketRepository repository;


    /**
     * Constructs a DeleteService with the specified ticket repository.
     * Uses dependency injection to maintain loose coupling between service and repository layers.
     *
     * @param repository The TicketRepository instance to be used for ticket operations
     */
    public DeleteService(TicketRepository repository) {
        this.repository = repository;
    }


    /**
     * Deletes a ticket from the system by its ID.
     * Delegates the delete operation to the repository layer.
     *
     * @param id The unique identifier of the ticket to be deleted
     * @return true if the ticket was successfully deleted, false if the ticket wasn't found
     */
    @Override
    public boolean delete(int id) {
        return repository.deleteTicket(id);
    }

    // Unused methods from interface
    @Override public void create(TicketModel ticket) {}
    @Override public TicketModel read(int id) { return null; }
    @Override public void update(TicketModel ticket) {}
}