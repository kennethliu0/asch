public class Result implements Comparable<Object>{
    private double score;
    private long team;
    public Result(double s, long t) {
        score = s;
        team = t;
    }
    public long getTeam() {
        return team;
    }
    public double getScore() {
        return score;
    }
    public String toString() {
        return "Score:  " + getScore() + "\nTeam: " + getTeam();
    }
    public int compareTo(Object obj) {
        assert obj instanceof Result;
        return Double.compare(((Result) obj).getScore(), this.getScore());
    } 
}
