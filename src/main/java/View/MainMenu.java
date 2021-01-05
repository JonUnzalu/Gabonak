/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author jonun
 */
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import Model.*;
import com.mongodb.client.model.Filters;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.bson.conversions.Bson;

public class MainMenu {

    static String dbName = "gabonak";
    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    static MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
    static MongoClient mongoClient = MongoClients.create(settings);
    static MongoDatabase database = mongoClient.getDatabase(dbName);
    static MongoCollection<StudentClass> collection = database.getCollection("studentgrades", StudentClass.class);

    static int inputStudent;
    static int inputClass;
    static int inputNewClass;

    static Consumer<StudentClass> printBlock = new Consumer<StudentClass>() {
        @Override
        public void accept(final StudentClass student) {
            System.out.format("%-10s %-10s %-150s",
                    student.getStudent_id(), student.getClass_id(), student.getScores());
            System.out.println();
        }
    };

    static Consumer<StudentClass> printBlockStudent = new Consumer<StudentClass>() {
        @Override
        public void accept(final StudentClass student) {
            if (student.getStudent_id() == inputStudent) {
                System.out.format("%-10s %-10s %-150s",
                        student.getStudent_id(), student.getClass_id(), student.getScores());
                System.out.println();
            }

        }
    };

    static Consumer<StudentClass> printBlockClass = new Consumer<StudentClass>() {
        @Override
        public void accept(final StudentClass student) {
            if (student.getClass_id() == inputClass) {
                System.out.format("%-10s %-10s %-150s",
                        student.getStudent_id(), student.getClass_id(), student.getScores());
                System.out.println();
            }

        }
    };

    static Consumer<StudentClass> printBlockAvgStudent = new Consumer<StudentClass>() {
        @Override
        public void accept(final StudentClass student) {
            DecimalFormat df = new DecimalFormat("###.##");

            if (student.getStudent_id() == inputStudent) {
                double sum = 0;

                for (int i = 0; i < student.getScores().size(); i++) {
                    sum = sum + student.getScores().get(i).getScore();
                }

                double avg = sum / student.getScores().size();
                String formatted = df.format(avg);

                System.out.format("%-10s %-10s %-10s",
                        student.getStudent_id(), student.getClass_id(), formatted);
                System.out.println();

            }
        }
    };

    static Consumer<StudentClass> printBlockAvgClass = new Consumer<StudentClass>() {
        @Override
        public void accept(final StudentClass student) {
            DecimalFormat df = new DecimalFormat("###.##");

            if (student.getClass_id() == inputClass) {
                double sum = 0;

                for (int i = 0; i < student.getScores().size(); i++) {
                    sum = sum + student.getScores().get(i).getScore();
                }

                double avg = sum / student.getScores().size();
                String formatted = df.format(avg);

                System.out.format("%-10s %-10s %-10s",
                        student.getStudent_id(), student.getClass_id(), formatted);
                System.out.println();
            }
        }
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean b = true;
        int aukera;
        System.out.println("-----------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>CHOOSE YOUR OPTION<<<<<<<<<<<<<<<<<<<<<");

        System.out.println("(1) Show all the registers");
        System.out.println("(2) Show all the registers of a student");
        System.out.println("(3) Show all the registers from a class");
        System.out.println("(4) Show the average score of a student in each class");
        System.out.println("(5) Show the average score of all the students from a class");
        System.out.println("(6) Add a register");
        System.out.println("(7) Edit a register");
        System.out.println("(8) Delete a register");
        System.out.println("(0) Exit");

        while (b == true) {
            System.out.println("Enter your option: ");
            aukera = in.nextInt();

            try {
                System.out.println("");

                switch (aukera) {
                    case 1:
                        showall();
                        break;

                    case 2:
                        showByStudentId();
                        break;

                    case 3:
                        showByClassId();
                        break;

                    case 4:
                        avgScoresStudent();
                        break;

                    case 5:
                        avgScoresClass();
                        break;

                    case 6:
                        addARegister();
                        break;

                    case 7:
                        editARegister();
                        break;

                    case 8:
                        deleteARegister();
                        break;

                    case 0:
                        System.out.println("Good bye!");
                        in.close();
                        mongoClient.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a number");
                in.next();
            }

            System.out.println("\n \n");
            System.out.println(">>>>>>>>>>>>>>>>>>>>CHOOSE YOUR OPTION<<<<<<<<<<<<<<<<<<<<<");

            System.out.println("(1) Show all the registers");
            System.out.println("(2) Show all the registers of a student");
            System.out.println("(3) Show all the registers from a class");
            System.out.println("(4) Show the average score of a student in each class");
            System.out.println("(5) Show the average score of all the students from a class");
            System.out.println("(6) Add a register");
            System.out.println("(7) Edit a register");
            System.out.println("(8) Delete a register");
            System.out.println("(0) Exit");
        }

    }

    private static void showall() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-150s", "STUDENT ID", "CLASS ID", "SCORES");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------");

        collection.find().forEach(printBlock);
    }

    private static void showByStudentId() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the id of the student: ");

            inputStudent = in.nextInt();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-10s %-150s", "STUDENT ID", "CLASS ID", "SCORES");
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------");

            collection.find().forEach(printBlockStudent);
        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }

    private static void showByClassId() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the id of the class: ");

            inputClass = in.nextInt();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-10s %-150s", "STUDENT ID", "CLASS ID", "SCORES");
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------");

            collection.find().forEach(printBlockClass);
        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }

    private static void avgScoresStudent() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the id of the student: ");

            inputStudent = in.nextInt();

            System.out.println("----------------------------------------------");
            System.out.printf("%-10s %-10s %-10s", "STUDENT ID", "CLASS ID", "SCORES AVERAGE");
            System.out.println("\n-------------------------------------------");

            collection.find().forEach(printBlockAvgStudent);
        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }

    private static void avgScoresClass() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the id of the class: ");

            inputClass = in.nextInt();

            System.out.println("----------------------------------------------");
            System.out.printf("%-10s %-10s %-10s", "STUDENT ID", "CLASS ID", "SCORES AVERAGE");
            System.out.println("\n-------------------------------------------");

            collection.find().forEach(printBlockAvgClass);
        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }

    private static void deleteARegister() {
        try {
            Scanner in = new Scanner(System.in);
            long countBefore = collection.countDocuments();

            System.out.println("Enter the id of the student: ");
            inputStudent = in.nextInt();

            System.out.println("Enter the id of the class: ");
            inputClass = in.nextInt();

            Bson filter1 = eq("student_id", inputStudent);
            Bson filter2 = eq("class_id", inputClass);
            collection.findOneAndDelete(Filters.and(filter1, filter2));

            long countAfter = collection.countDocuments();
            if (countBefore > countAfter) {
                System.out.println("The register was successfully deleted");
            } else {
                System.out.println("The combination of student and class you entered doesn't exist");
            }
        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }

    private static void addARegister() {
        try {
            Scanner in = new Scanner(System.in);
            long countBefore = collection.countDocuments();

            List<Score> newScores = new ArrayList<>();

            System.out.println("Enter the id of the student: ");
            inputStudent = in.nextInt();

            System.out.println("Enter the id of the class: ");
            inputClass = in.nextInt();

            System.out.println("Now we will start with the tasks \n");

            while (true) {
                System.out.println("Enter the type of task (h:homework/e:exam/q:quiz): ");
                char c = in.next().toLowerCase().charAt(0);
                System.out.toString();

                System.out.println("Now enter the score of the task (out of 100): ");
                double score = in.nextDouble();
                System.out.toString();

                if (score > 100 || score < 0) {
                    System.out.println("The score can't be more than 100 or less than 0");
                } else {
                    if (c == 'h') {
                        newScores.add(new Score("homework", score));
                    } else if (c == 'e') {
                        newScores.add(new Score("exam", score));
                    } else if (c == 'q') {
                        newScores.add(new Score("quiz", score));
                    } else {
                        System.out.println("Please enter a valid task option");
                    }

                }
                System.out.println("Do you want to add another task? (1: yes, Any other key:no)");
                String option = in.next();

                if (!option.equals("1")) {
                    break;
                }
            }

            StudentClass toAdd = new StudentClass(inputStudent, inputClass, newScores);
            collection.insertOne(toAdd);

            long countAfter = collection.countDocuments();
            if (countBefore < countAfter) {
                System.out.println("The register was successfully added");
            } else {
                System.out.println("The combination of student and class you entered doesn't exist");
            }
        } catch (InputMismatchException ime) {
            System.out.println("You must enter the requested datatype (when you add scores use , instead of .)");
        }
    }

    private static void editARegister() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the id of the student: ");
            inputStudent = in.nextInt();

            System.out.println("Enter the id of the class you want to change: ");
            inputClass = in.nextInt();

            System.out.println("Enter the id of the new class: ");
            inputNewClass = in.nextInt();

            Bson filter1 = eq("student_id", inputStudent);
            Bson filter2 = eq("class_id", inputClass);
            Bson filterNew = eq("class_id", inputNewClass);

            collection.updateOne(Filters.and(filter1, filter2), filterNew);

        } catch (InputMismatchException ime) {
            System.out.println("You have to enter a number");
        }
    }
}
