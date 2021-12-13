
package package1;

public class Position {
    public int x = 0;
    public int y = 0;
    public Position(int x, int y){
        this.x = x;
        this.y = y;

    }
    public boolean equals(int x, int y)
    {
        return this.x == x && this.y == y;
    }
}
