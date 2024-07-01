package goit.lecture.feature.ticket;

import lombok.Data;

@Data
public class TicketEntity {
    private long id;
    private long passengerId;
    private Planet to;
    private Planet from;


}
