/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jonun
 */
public class StudentClass {
    private ObjectId identifier;
    private int student_id;
    private int class_id;
    private List<Score> scores;

    public StudentClass() {
    }

    public StudentClass(ObjectId identifier, int student_id, int class_id, List<Score> scores) {
        this.identifier = identifier;
        this.student_id = student_id;
        this.class_id = class_id;
        this.scores = scores;
    }

    public StudentClass(int student_id, int class_id, List<Score> scores) {
        this.student_id = student_id;
        this.class_id = class_id;
        this.scores = scores;
    }

    public ObjectId getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ObjectId identifier) {
        this.identifier = identifier;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "student_id=" + student_id + " class_id=" + class_id + " scores=" + scores + '}';
    }
    
    
}
