import java.util.*;

public class prob4{
    static int total = 0;

    public static void main(String[] args) {
        char[][] matrix = {
                {'0', '1', '1', '1', '1', '1', '1'},
                {'2', '2', 'A', '2', '2', 'B', '1'},
                {'2', '1', '1', '1', '1', '1', '1'},
                {'2', '1', '2', '2', '2', '2', '2'},
                {'2', '1', 'C', '1', '1', '1', '1'},
                {'2', '1', '1', '1', '2', 'D', '1'},
                {'2', '2', '2', '2', '2', '2', '1'}
        };

        HashMap<Character, List<Integer>> map = new HashMap<>();
        map.put('A', Arrays.asList(3, 2));
        map.put('B', Arrays.asList(1, 4));
        map.put('C', Arrays.asList(2, 2));
        map.put('D', Arrays.asList(1, 3));

        List<Integer> aliens = new ArrayList<>();
        aliens.add(Integer.MAX);
        aliens.add(20);
        aliens.add(10);

        helper(aliens, 0, matrix, map);

        System.out.print(total);
    }

    private static void helper(List<Integer> aliens, int index, char[][] matrix, HashMap<Character, List<Integer>> map) {
        if (index == aliens.size()) {
            return;
        }

        int health = aliens.get(index);
        total += traverse(health, matrix, map, 0, 0);

        helper(aliens, index + 1, matrix, map);
    }

    private static int traverse(int health, char[][] matrix, HashMap<Character, List<Integer>> map, int row, int col) {
        int[] directions = {-1, 0, 1, 0, -1};

        return dfs(health, matrix, map, row, col, directions);
    }

    private static int dfs(int health, char[][] matrix, HashMap<Character, List<Integer>> map, int row, int col, int[] directions) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] == '0') {
            return 0;
        }

        if (map.containsKey(matrix[row][col])) {
            for (Character turretKey : map.keySet()) {
                List<Integer> turretInfo = map.get(turretKey);
                int range = turretInfo.get(0);
                int damage = turretInfo.get(1);

                int alienRow = map.get(turretKey).get(0);
                int alienCol = map.get(turretKey).get(1);

                int distance = Math.abs(row - alienRow) + Math.abs(col - alienCol);

                if (distance <= range) {
                    health -= damage;
                }
            }
        }

        char original = matrix[row][col];
        matrix[row][col] = '0';

        int totalHealth = health;

        for (int i = 0; i < 4; i++) {
            int newRow = row + directions[i];
            int newCol = col + directions[i + 1];
            totalHealth += dfs(health, matrix, map, newRow, newCol, directions);
        }

        matrix[row][col] = original;

        return totalHealth;
    }
}
