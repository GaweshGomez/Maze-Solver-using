import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ArrayList<String[]> maze = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File filename = new File("maze.txt");
        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] list = line.split("");
            maze.add(list);
        }
        int startingRow = -1;
        int startingCol = -1;
        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j < maze.get(i).length; j++) {
                if (maze.get(i)[j].equalsIgnoreCase("S")) {
                    startingRow = i;
                    startingCol = j;
                }
            }
        }
        int count =0;
        if (maze.get(startingRow)[startingCol+1].equals(".")){
            count++;
        }
        if (maze.get(startingRow+1)[startingCol].equals(".")){
            count++;
        }
        if (maze.get(startingRow)[startingCol-1].equals(".")){
            count++;
        }
        if (maze.get(startingRow-1)[startingCol].equals(".")){
            count++;
        }
        System.out.println(count);
        System.out.println(startingRow + " " + startingCol);

        for (int number=1; number<=count;number++){
            for (int i = 0; i < maze.size(); i++) {
                for (int j = 0; j < maze.get(i).length; j++) {
                    if (maze.get(i)[j].equalsIgnoreCase("S")) {
                        startingRow = i;
                        startingCol = j;
                    }
                }
            }
            System.out.println(startingRow + " " + startingCol);
            StackAsLinkedList coordinate = new StackAsLinkedList();
            List<String> movement = new ArrayList<>();
            String lastMove = null;
            while (true) {
                if(!coordinate.isEmpty()) {
                    String values = coordinate.peek();
                    int x = Integer.parseInt(values.substring(0, 1));
                    int b = Integer.parseInt(values.substring(1, 2));
                    maze.get(x)[b]= "X";
                }
                //Right
                if (maze.get(startingRow)[startingCol + 1].equalsIgnoreCase("E")) {
                    movement.add("Move Right\nYou won");
                    System.out.println(movement.get(movement.size()-1));
                    number = count+1;
                    break;
                }else if (maze.get(startingRow)[startingCol + 1].equalsIgnoreCase(".")) {
                    coordinate.push(String.valueOf(startingRow) + (++startingCol));
                    movement.add("Move Right");
                    System.out.println(movement.get(movement.size()-1));
                    continue;
                }
                //Down
                if (maze.get(startingRow + 1)[startingCol].equalsIgnoreCase("E")) {
                    movement.add("Move Down\nYou won");
                    System.out.println(movement.get(movement.size()-1));
                    number = count+1;
                    break;
                } else if (maze.get(startingRow + 1)[startingCol].equalsIgnoreCase(".")) {
                    coordinate.push(String.valueOf(++startingRow) + (startingCol));
                    movement.add("Move Down");
                    System.out.println(movement.get(movement.size()-1));
                    continue;

                }
                //Left
                if (maze.get(startingRow)[startingCol - 1].equalsIgnoreCase("E")) {
                    movement.add("Move Right\nYou won");
                    System.out.println(movement.get(movement.size()-1));
                    number = count+1;
                    break;
                } else if (maze.get(startingRow)[startingCol - 1].equalsIgnoreCase(".")) {
                    coordinate.push(String.valueOf(startingRow) + (--startingCol));
                    movement.add("Move Left");
                    System.out.println(movement.get(movement.size()-1));
                    continue;
                }
                //Up
                if (maze.get(startingRow - 1)[startingCol].equalsIgnoreCase("E")) {
                    movement.add("Move Up\nYou won");
                    System.out.println(movement.get(movement.size()-1));
                    number = count+1;
                    break;

                } else if (maze.get(startingRow - 1)[startingCol].equalsIgnoreCase(".")) {
                    coordinate.push(String.valueOf(--startingRow) + (startingCol));
                    movement.add("Move Left");
                    System.out.println(movement.get(movement.size()-1));
                }else{
                    coordinate.pop();
                    if(coordinate.isEmpty()){
                        System.out.println("Path not found!");
                        System.out.println("That path is wrong");
                        System.out.println();
                        break;
                    }
                    if(lastMove == "Move Right"){
                        startingCol--;
                        movement.remove(movement.size()-1);
                        System.out.println("Back track Left");
                    } else if (lastMove == "Move Down") {
                        startingRow--;
                        System.out.println("Back track Up");
                        movement.remove(movement.size()-1);
                    }else if (lastMove == "Move Left"){
                        startingCol++;
                        movement.remove(movement.size()-1);
                        System.out.println("Back track Right");
                    }else if (lastMove == "Move Up"){
                        startingRow++;
                        movement.remove(movement.size()-1);
                        System.out.println("Back track Down");
                    }
                }
                lastMove = movement.get(movement.size()-1);
            }
        }



        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j <maze.get(i).length ; j++) {
                System.out.print(maze.get(i)[j]+" ");
            }
            System.out.println();
        }
    }
}
