package services;

import models.TicketModel;
import repository.TicketRepository;


/**
 * Abstract service class implementing the CRUDService interface, specifically focused on ticket creation.
 * This class demonstrates the Single Responsibility Principle by handling only creation-related operations.
 */
public class CreateService implements CRUDService {

    /**
     * Repository instance for ticket data operations.
     */
    private TicketRepository repository;


    /**
     * Constructs a CreateService with the specified ticket repository.
     *
     * @param repository The TicketRepository instance to be used for ticket operations
     */
    public CreateService(TicketRepository repository) {
        this.repository = repository;
    }


    /**
     * Creates a new ticket in the system.
     * Implementation is left to concrete classes.
     *
     * @param ticket The TicketModel object to be created
     */
    @Override
    public void create(TicketModel ticket) {
        repository.createTicket(ticket);
    }

    // Unused methods from interface - demonstrate polymorphism
    @Override public TicketModel read(int id) { return null; }
    @Override public void update(TicketModel ticket) {}
    @Override public boolean delete(int id) { return false; }
}