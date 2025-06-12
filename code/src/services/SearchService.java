package services;

import models.TicketModel;
import repository.TicketRepository;
import java.util.List;

/**
 * Service class providing search functionality for tickets in the Call Center Ticketing System.
 * This class implements basic search mechanisms for finding tickets based on various criteria.
 */
public class SearchService {
    /**
     * Repository instance for ticket data operations.
     */
    private TicketRepository repository;

    /**
     * Constructs a SearchService with the specified ticket repository.
     *
     * @param repository The TicketRepository instance to be used for ticket search operations
     */
    public SearchService(TicketRepository repository) {
        this.repository = repository;
    }

    /**
     * Searches for tickets using multiple criteria.
     * If any criterion is null or empty, it will be ignored in the search.
     *
     * @param customerName The customer name to search for (can be null)
     * @param category The category to search for (can be null)
     * @param status The status to search for (can be null)
     * @param priority The priority to search for (can be null)
     * @return List of tickets matching all non-null criteria
     */
    public List<TicketModel> searchByCriteria(String customerName, String category,
                                              String status, String priority) {
        return repository.searchByCriteria(customerName, category, status, priority);
    }
}