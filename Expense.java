public class Expense {
    private String date;
    private String category;
    private double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String toString() {
        return date + "," + category + "," + amount;
    }

    public static Expense fromString(String data) {
        String[] parts = data.split(",");
        return new Expense(parts[0], parts[1], Double.parseDouble(parts[2]));
    }

    public String getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
}
