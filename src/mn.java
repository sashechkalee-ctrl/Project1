import java.util.Random;
import java.util.Scanner;

public class mn {
    public static void main(String[] args) {
        String monster = " \ud83e\udddf ";
        String castle = " З  ";
        Random random = new Random();
        int sizeBoard = 5;
        Person person = new Person(sizeBoard);
        int step = 0;
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 0;
        int monsterChance;
        String[][] board = new String[sizeBoard][sizeBoard];

        for(int i = 0; i < sizeBoard; ++i) {
            for(int j = 0; j < sizeBoard; ++j) {
                monsterChance = random.nextInt(5);
                if (monsterChance != 1) {
                    board[i][j] = monster;
                } else {
                    board[i][j] = "    ";
                }
            }
        }

        board[castleY][castleX] = castle;
        board[person.getY()][person.getX()] = person.image;
        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        System.out.println("Ваш ответ:\t" + answer);
        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = scanner.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (person.getLive() != 0 || person.getX() != castleX && person.getY() != castleY) {
                    outputBoard(board, sizeBoard, person.getLive());
                    System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на 1)\nКоординаты персонажа - (x:" + (person.getX() + 1) + ", y: " + (person.getY() + 1) + ")");
                    int x = scanner.nextInt() - 1;
                    int y = scanner.nextInt() - 1;
                    System.out.println((x + 1) + ", " + (y + 1));

                    if (person.isMoveCorrect(x, y)) {
                        board[person.getY()][person.getX()] = "    ";
                        person.move(x,y);
                        ++step;
                        System.out.println("Ход корректный; Новые координаты: " + (person.getX() + 1) + ", " + (person.getY() + 1) + "\nХод номер: " + step);
                        if (board[person.getY()][person.getX()].equals(monster)) {
                            if (taskMonster(difficultGame)) {
                                System.out.println("Правильно, ты проходишь дальше.");
                            } else {
                                System.out.println("Неверно, монстр ударил тебя!");
                                person.setLive(person.getLive() - 1);
                            }
                        }

                        board[person.getY()][person.getX()] = person.image;
                    } else if (x != person.getX() && y != person.getY()) {
                        System.out.println("Некорректный ход");
                    } else {
                        System.out.println("Координаты не изменены");
                    }

                    if (person.getX() == castleX && person.getY() == castleY) {
                        System.out.println("Ты победил! Ты затратил " + step + " шагов.");
                        break;
                    } else if (person.getLive() == 0){
                        System.out.println("Ты проиграл!");
                        break;
                    }
                }
            case "НЕТ":
                System.out.println("Жаль, приходи еще!");
                break;
            default:
                System.out.println("Данные введены некоректно");
        }

    }
    static boolean taskMonster(int difficult) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int a = random.nextInt(300);
        int b = random.nextInt(300);
        int trueAns = a + b;
        System.out.println("На вас напал монстр! Скоолько будет " + a + " + " + b + "?");
        int ans = scanner.nextInt();
        return ans == trueAns;
    }
    static void outputBoard(String[][] board, int sizeBoard, int personLive){
        for(int i = 0; i < sizeBoard; ++i) {
            System.out.println("+ —— ".repeat(sizeBoard) + "+");

            for(int j = 0; j < sizeBoard; ++j) {
                System.out.print("|" + board[i][j]);
            }

            System.out.println("|");
        }

        System.out.println("+ —— ".repeat(sizeBoard) + "+");
        System.out.println("Количество жизней:\t" + personLive + "\n");
    }
}


