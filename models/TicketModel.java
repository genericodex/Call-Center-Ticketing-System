package models;

public class TicketModel {
        private int id;
        private String customerName;
        private String contactInfo;
        private String category;
        private String description;
        private String status;
        private String priority;
        private String comments;


    /**
     * Constructor to create a new ticket with initial values.
     * Sets default values for status, priority, and comments.
     *
     * @param id Unique identifier for the ticket
     * @param customerName Name of the customer
     * @param contactInfo Customer's contact information
     * @param category Category of the ticket
     * @param description Detailed description of the issue
     */


    public TicketModel(int id, String customerName, String contactInfo,
                      String category, String description) {
            this.id = id;
            this.customerName = customerName;
            this.contactInfo = contactInfo;
            this.category = category;
            this.description = description;
            this.status = "Open";
            this.priority = "Medium";
            this.comments = "";
        }

        // Getters
        public int getId() { return id; }
        public String getCustomerName() { return customerName; }
        public String getContactInfo() { return contactInfo; }
        public String getCategory() { return category; }
        public String getDescription() { return description; }
        public String getStatus() { return status; }
        public String getPriority() { return priority; }
        public String getComments() { return comments; }

    // Setter methods - only for fields that should be updatable
    /**
     * Updates the ticket's status.
     * @param status new status value
     */

    public void setStatus(String status) { this.status = status; }

    /**
     * Updates the ticket's priority.
     * @param priority new priority value
     */

    public void setPriority(String priority) { this.priority = priority; }

    /**
     * Adds a new comment to the ticket's comment history.
     * If comments already exist, adds a newline before the new comment.
     *
     * @param comment The comment text to add
     */

    public void addComment(String comment) {
            if (!comments.isEmpty()) comments += "\n";
            comments += comment;
        }
    /**
     * Overrides the default toString method to provide a formatted string
     * representation of the ticket.
     * The @Override annotation indicates this method is overriding a method
     * from a parent class (Object).
     *
     * @return formatted string containing all ticket information
     */

    @Override
    public String toString() {
            return "ID: " + id +
                    "\nCustomer: " + customerName +
                    "\nContact: " + contactInfo +
                    "\nCategory: " + category +
                    "\nDescription: " + description +
                    "\nStatus: " + status +
                    "\nPriority: " + priority +
                    "\nComments:\n" + (comments.isEmpty() ? "No comments" : comments) +
                    "\n----------------------------------";
        }
}
