package fitness.application.exceptions;

public class exerciseAlreadyExists extends Exception{
    private String name;

    public exerciseAlreadyExists(String name) {
        super(String.format("An exercise with the name %s already exists!", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
