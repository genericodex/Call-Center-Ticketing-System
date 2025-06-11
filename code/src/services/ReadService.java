package services;

import models.TicketModel;
import repository.TicketRepository;

import java.util.List;


/**
 * Service class implementing the CRUDService interface, specifically focused on ticket reading operations.
 * This class demonstrates the Single Responsibility Principle by handling only read-related operations.
 */
public class ReadService implements CRUDService {

    /**
     * Repository instance for ticket data operations.
     */
    private TicketRepository repository;

    /**
     * Constructs a ReadService with the specified ticket repository.
     *
     * @param repository The TicketRepository instance to be used for ticket operations
     */
    public ReadService(TicketRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param id The unique identifier of the ticket to retrieve
     * @return The TicketModel object if found, null otherwise
     */
    @Override
    public TicketModel read(int id) {
        return repository.getTicketById(id);
    }

    /**
     * Retrieves all tickets from the repository.
     *
     * @return A List containing all TicketModel objects in the system
     */
    public List<TicketModel> readAll() {
        return repository.getAllTickets();
    }

    // Unused methods from interface
    @Override public void create(TicketModel ticket) {}
    @Override public void update(TicketModel ticket) {}
    @Override public boolean delete(int id) { return false; }
}