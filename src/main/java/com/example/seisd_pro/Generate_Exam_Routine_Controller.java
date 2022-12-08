package com.example.seisd_pro;

import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Generate_Exam_Routine_Controller {

    static Connection c1;
    static Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> Col_Course_Name;

    @FXML
    private TableColumn<?, ?> Col_Exam_Date;

    @FXML
    private TableColumn<?, ?> Col_Room;

    @FXML
    private TableColumn<?, ?> Col_Time;
    @FXML
    private TableColumn<?, ?> Col_Exam_No;

    @FXML
    private TableView<ExamScheduleTableClass> ESchedule_table;

    @FXML
    private TextField End_time1;

    @FXML
    private DatePicker Exam_Offdate;

    @FXML
    private DatePicker Exam_Startdate;

    @FXML
    private TextField RoomNo;

    @FXML
    private TextField Start_time1;

    @FXML
    private TextField Start_time2;

    @FXML
    private TextField End_time2;

    @FXML
    private TextField Ucourse;

    @FXML
    private DatePicker Udate;

    @FXML
    private TextField UroomNo;

    @FXML
    private TextField Utime;

    @FXML
    private TableColumn<?, ?> col_offday;

    @FXML
    private Button create;

    @FXML
    private Label error;

    @FXML
    private Button gadd;

    @FXML
    private Button gupdate;

    @FXML
    private TextField NoOfSelectedExam;

    @FXML
    private TableView<offDayListTableClass> off_table;

    @FXML
    private Button publish;

    private ArrayList<LocalDate> offDayList = new ArrayList<>(); ///off day list for skip exam on that day

    @FXML
    void OffDayAddButton(ActionEvent event) {


        if(!offDayList.contains(Exam_Offdate.getValue())  && Exam_Offdate.getValue()!=null){
            offDayList.add(Exam_Offdate.getValue());
            offDayListTableClass offdayObj = new offDayListTableClass(Exam_Offdate.getValue());
            off_table.getItems().add(offdayObj);
            Exam_Offdate.setValue(null);
        }


    }

    static LocalDate examStartDay;
    String allocatedRooms,StartTime1,StartTime2,EndTime1,EndTime2;
    ArrayList<String> takenExamBatchShift = new ArrayList<>();
    ArrayList<String> takenExamBatchShift1 = new ArrayList<>();
    ArrayList<String> takenExamBatchShift2 = new ArrayList<>();
    private static String getJsonText(String order) throws SQLException {

        String JsonText ="";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {JsonText=r.getString("value");}
        return JsonText;
    }

    private  static  JSONObject getJsonObj(String JSONTEXT){
        Object obj =JSONValue.parse(JSONTEXT);
        JSONObject jsonObj = (JSONObject) obj;
        return  jsonObj;
    }
    String fixedTime="";
    int fixedRoom=1;
    int fixedShift =1;
    int Batchcount =0;
    String tempCourse="";
    int count = 1;
    @FXML
    void CreateRoutineButton(ActionEvent event) throws SQLException {
        error.setText("");


        examStartDay= Exam_Startdate.getValue();
        allocatedRooms = RoomNo.getText().toString().trim();
        StartTime1 = Start_time1.getText().toString().trim();
        StartTime2 = Start_time2.getText().toString().trim();
        EndTime1 = End_time1.getText().toString().trim();
        EndTime2 = End_time2.getText().toString().trim();

        ExamScheduleTableClass examScheduleTableObj;

        fixedTime+= StartTime1+"-"+EndTime1;

        //given room for exam
        String regex = "^(.*?)-(.*?)-(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(allocatedRooms);
        matcher.find();  //matcher.group(1) = 111 ,matcher.group(2) = 112,matcher.group(3) = 113


        if((examStartDay != null && matcher.matches() ==true && utilities.isValidTime(StartTime1.trim()) ==true && utilities.isValidTime(StartTime2)==true && utilities.isValidTime(EndTime1)==true  && utilities.isValidTime(EndTime2)==true) ){

            // Get info about the semester
            String offeredCourseJsonText = getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
            JSONObject CourseOFBatches_JsonObj = getJsonObj(offeredCourseJsonText); //all course name of that batch
            JSONArray assignedCourse ;

            // Get info of batches according to the course of the semester
            String runningCourseJsonData =getJsonText("SELECT * FROM `information` WHERE attribute ='runningCourseData'");
            JSONObject assignedBatchOfThatCourse_JsonObj = getJsonObj(runningCourseJsonData);//batch name that are assigned to that course
            JSONArray assignedBatch ;


            List<String> totalCourse_FOR_LOOP_TRAVERSAL = new ArrayList<String>(assignedBatchOfThatCourse_JsonObj.keySet());  //keyset-- total course count

            //keyset of jar array --- the batch no in the List
            List<String> batchNo = new ArrayList<String>(CourseOFBatches_JsonObj.keySet());



            while(totalCourse_FOR_LOOP_TRAVERSAL.size()>0){

                assignedCourse= (JSONArray) CourseOFBatches_JsonObj.get(batchNo.get(Batchcount));
                System.out.println("start "+batchNo.get(Batchcount) +"shift : "+fixedShift);




                //if the batch is assigned for that day
//                if((takenExamBatchShift1.contains(batchNo.get(Batchcount)) && fixedShift==1) || (takenExamBatchShift2.contains(batchNo.get(Batchcount)) && fixedShift==2)){
//                    continue;
//                }

                if(assignedCourse.size()>0){
                    if(fixedShift==1){
                        for (int i = 0; i < takenExamBatchShift1.size(); i++) {
                            System.out.print(takenExamBatchShift1.get(i)+" ");
                        }
                    } else if (fixedShift==2) {
                        for (int i = 0; i < takenExamBatchShift2.size(); i++) {
                            System.out.print(takenExamBatchShift2.get(i)+" ");
                        }
                    }
                    System.out.println("close");


                    tempCourse = String.valueOf(assignedCourse.get(0));
                    assignedBatch = (JSONArray) assignedBatchOfThatCourse_JsonObj.get(tempCourse); // batch data collect of that course
                    System.out.println("for course"+tempCourse+" batch are"+assignedBatch);

                    for (int i = 0; i < assignedBatch.size(); i++) {

                        if(fixedShift==1){
                            takenExamBatchShift1.add(assignedBatch.get(i).toString());
                        }
                        else {
                            takenExamBatchShift2.add(assignedBatch.get(i).toString());
                        }
                        takenExamBatchShift.add(assignedBatch.get(i).toString());

                    }
                    //delete that course from the conflict and that own batch
                    for (int i = 0; i < assignedBatch.size(); i++) {
                        assignedCourse = (JSONArray) CourseOFBatches_JsonObj.get(assignedBatch.get(i).toString());

                        assignedCourse.remove(tempCourse);
                    }


                    examScheduleTableObj= new ExamScheduleTableClass(count,examStartDay,tempCourse,fixedTime,matcher.group(fixedRoom));
                    ESchedule_table.getItems().add(examScheduleTableObj);
                    count++;


                    //room changing
                    if(fixedRoom ==3){
                        fixedRoom=1;  //if all room are occupied than set the first room
                        fixedShift++;   //increase shift to go next shift means ++, and next shift occupied than go to shift 1
                    }else{
                        fixedRoom++;
                    }


                    if(fixedShift==1) {
                        fixedTime="";
                        fixedTime+= StartTime1+"-"+EndTime1;
                    }else{
                        fixedTime="";
                        fixedTime+= StartTime2+"-"+EndTime2;

                        fixedShift = 1;
                    }

                    //date changing if exam all are taken for all batch
                    if(takenExamBatchShift.size()== batchNo.size() || offDayList.contains(examStartDay)==true){
                        takenExamBatchShift.clear();
                        takenExamBatchShift1.clear();
                        takenExamBatchShift2.clear();
                        examStartDay = examStartDay.plusDays(1);

                    }


                }


                totalCourse_FOR_LOOP_TRAVERSAL.remove(tempCourse);  //remove for loop traversal ,it will descrease size to end
                System.out.println("");
                //loop for batch traversal

               if(Batchcount == (batchNo.size()-1)){
                   Batchcount=0;
               }else{
                   Batchcount++;
               }

               //break while loop when all assign completed
                if (totalCourse_FOR_LOOP_TRAVERSAL.size()==0){
                    error.setText("Completed");
                    break;
                }
            }



//            System.out.println(CourseOFBatches_JsonObj);
//            String jsonT = JSONValue.toJSONString(CourseOFBatches_JsonObj);
//            System.out.print(jsonT);
//            System.out.println(assignedBatchOfThatCourse_JsonObj);


        }else{
           error.setText("Input Format or data is not correctly given.Try Again !!");
        }


    }
 private  static  void setRoutine(){

 }
    @FXML
    void mouseEvent(MouseEvent event) {
        ExamScheduleTableClass clickedRow = ESchedule_table.getSelectionModel().getSelectedItem();
        NoOfSelectedExam.setText(String.valueOf(clickedRow.getNo()));
        Udate.setValue(clickedRow.getExamDate());
        Ucourse.setText(String.valueOf(clickedRow.getCourseName()));
        Utime.setText(String.valueOf(clickedRow.getExamTime()));
        UroomNo.setText(String.valueOf(clickedRow.getExamRoom()));

    }


    @FXML
    void IndividualUpdateRoutineButton(ActionEvent event) {
        ObservableList<ExamScheduleTableClass> currentTableData = ESchedule_table.getItems();
        int currentExamNo = Integer.parseInt(NoOfSelectedExam.getText());

        for (ExamScheduleTableClass examScheduleTable:  currentTableData) {
            if (examScheduleTable.getNo()==currentExamNo){
                examScheduleTable.setExamDate(Udate.getValue());
                examScheduleTable.setCourseName(Ucourse.getText().trim());
                examScheduleTable.setExamRoom(UroomNo.getText().trim());
                examScheduleTable.setExamTime(Utime.getText().trim());

                ESchedule_table.setItems(currentTableData);
                ESchedule_table.refresh();
                break;
            }

        }

    }


    @FXML
    void RoutinePublishButton(ActionEvent event) {

    }
    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


        NoOfSelectedExam.setEditable(false);
        col_offday.setCellValueFactory(new PropertyValueFactory<>("OffDay"));
        Col_Exam_No.setCellValueFactory(new PropertyValueFactory<>("no"));
        Col_Exam_Date.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        Col_Course_Name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        Col_Time.setCellValueFactory(new PropertyValueFactory<>("examTime"));
        Col_Room.setCellValueFactory(new PropertyValueFactory<>("ExamRoom"));


    }

}
