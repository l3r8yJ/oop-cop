class ValidClass {
    private final String name;

    ValidClass(String n) {
        this.name = n;
    }

    public void SayHi() {
        System.out.println("Hi " + name);
    }

    public String getName() {
        return name;
    }
}