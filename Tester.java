public class Tester {
    public static void main(String[] args) {
        String filePath = "src\\illnesses.txt";
        PhysiciansHelper<String, String> helper = new PhysiciansHelper<>(filePath);
        helper.add("Ken", "low fever");
        helper.add("Ken", "high fever");
        helper.add("Ken", "cough");
        helper.add("May", "high fever");
        helper.display();
    }
}
