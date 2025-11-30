public class Table {

    int forks;

    public Table(int forks) {
        this.forks = forks;
    }

    public boolean forkAvailable() {
        return forks > 0;
    }

    public void takeFork() {
        forks--;
    }

    public void returnFork() {
        forks++;
    }

    public boolean askForPermission() {
        if(Main.iterations == 0) {
            if(forks == 1) {
                Main.iterations = 1;
                return false;
            }
            return true;
        }
        Main.iterations = 0;
        return true;
    }

}