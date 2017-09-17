public class ItemClass {
    public String name;
    public int weight;

    public ItemClass(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + weight;
    }
}
