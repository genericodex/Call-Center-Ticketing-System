package services;

import models.TicketModel;
import repository.TicketRepository;
import java.util.List;
import java.util.function.Predicate;


/**
 * Service class providing search functionality for tickets in the Call Center Ticketing System.
 * This class implements a flexible search mechanism using predicates for custom search criteria.
 * Note that this service is separate from CRUD operations as it provides specialized search functionality.
 */
public class SearchService {

    /**
     * Repository instance for ticket data operations.
     * Handles the actual storage and retrieval of ticket data.
     */
    private TicketRepository repository;


    /**
     * Constructs a SearchService with the specified ticket repository.
     * Uses dependency injection to maintain loose coupling between service and repository layers.
     *
     * @param repository The TicketRepository instance to be used for ticket search operations
     */
    public SearchService(TicketRepository repository) {
        this.repository = repository;
    }


    /**
     * Searches for tickets that match the specified criteria defined by the predicate.
     * Provides a flexible way to search tickets using lambda expressions or method references.
     *
     * Example usage:
     * <pre>
     * // Search for high priority tickets
     * searchService.search(ticket -> ticket.getPriority() == Priority.HIGH);
     *
     * // Search for tickets assigned to specific agent
     * searchService.search(ticket -> "AgentName".equals(ticket.getAssignedTo()));
     * </pre>
     *
     * @param predicate A functional interface defining the search criteria for tickets
     * @return List of TicketModel objects that match the specified predicate
     */
    public List<TicketModel> search(Predicate<TicketModel> predicate) {
        return repository.searchTickets(predicate);
    }
}