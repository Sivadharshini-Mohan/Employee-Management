import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TraineeDao {
    static List<Trainee> trainees = new LinkedList<Trainee>();

    /**
     * <p>
     * Insert the trainee into the list
     * </p>
     * @param trainee
     * 
     */
    public void insertTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    /**
     * <p>
     * Retrive the trainee from the list
     * </p>
     * @return trainees 
     * 
     */
    public List<Trainee> retriveTrainee() {       
        return trainees;
    }
   
  /**
     * <p>
     * update the exsiting trainee in the list
     * </p>
     * @return trainees 
     * 
     */
    public void updateByIndex(int index, Trainee trainee) {
        trainees.set(index,trainee);
    }

   /**
     * <p>
     * delete the exsiting trainee in the list
     * </p>
     * @return trainees 
     * 
     */
     public void deleteTraineeByObject(Trainee trainee) {
        trainees.remove(trainee);
    }
    
}
