package services;

import models.TicketModel;

public interface CRUDService {
    void create(TicketModel ticket);
    TicketModel read(int id);
    void update(TicketModel ticket);
    boolean delete(int id);
}