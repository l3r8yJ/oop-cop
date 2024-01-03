import java.io.File;

@SuppressWarnings({"OOP.WorkerCheck", "OOP.MutableStateCheck"})
class Parser {
    private File file;

    public void setName(final File file) {
        this.file = file;
    }
}
