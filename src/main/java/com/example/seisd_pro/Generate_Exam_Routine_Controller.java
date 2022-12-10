package com.example.seisd_pro;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import static java.lang.System.gc;

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


        if (!offDayList.contains(Exam_Offdate.getValue()) && Exam_Offdate.getValue() != null) {
            offDayList.add(Exam_Offdate.getValue());
            offDayListTableClass offdayObj = new offDayListTableClass(Exam_Offdate.getValue());
            off_table.getItems().add(offdayObj);
            Exam_Offdate.setValue(null);
        }


    }

    static LocalDate examStartDay;
    String allocatedRooms, StartTime1, StartTime2, EndTime1, EndTime2;
    ArrayList<String> takenExamBatchShift = new ArrayList<>();
    ArrayList<String> takenExamBatchShift1 = new ArrayList<>();
    ArrayList<String> takenExamBatchShift2 = new ArrayList<>();


    static BorderPane borderPane;
    static  void getBorderPane(BorderPane borderPane){
        Generate_Exam_Routine_Controller.borderPane = borderPane;
    }


    String fixedTime = "";
    int fixedRoom = 1;
    int fixedShift = 1;
    int Batchcount = 0;
    String tempCourse = "";
    int count = 1;
    // Get info about the semester
    String offeredCourseJsonText,runningCourseJsonData;
    JSONObject CourseOFBatches_JsonObj,assignedBatchOfThatCourse_JsonObj;
    JSONArray assignedCourse,assignedBatch;

    ExamScheduleTableClass examScheduleTableObj;
    Matcher matcher;
    List<String> batchNo;
    List<String> totalCourse_FOR_LOOP_TRAVERSAL;

    Boolean avaialable =false, otherbatchExamOnThatDay =true;


    private static String getJsonText(String order) throws SQLException {

        String JsonText = "";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            JsonText = r.getString("value");
        }
        return JsonText;
    }

    private static JSONObject getJsonObj(String JSONTEXT) {
        Object obj = JSONValue.parse(JSONTEXT);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }
    @FXML
    void CreateRoutineButton(ActionEvent event) throws SQLException {
        error.setText("");

        gc();



        examStartDay = Exam_Startdate.getValue();
        allocatedRooms = RoomNo.getText().toString().trim();
        StartTime1 = Start_time1.getText().toString().trim();
        StartTime2 = Start_time2.getText().toString().trim();
        EndTime1 = End_time1.getText().toString().trim();
        EndTime2 = End_time2.getText().toString().trim();


        fixedTime += StartTime1 + "-" + EndTime1;

        //given room for exam
        String regex = "^(.*?)-(.*?)-(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(allocatedRooms);
        matcher.find();  //matcher.group(1) = 111 ,matcher.group(2) = 112,matcher.group(3) = 113


        if ((examStartDay != null && matcher.matches() == true && utilities.isValidTime(StartTime1.trim()) == true && utilities.isValidTime(StartTime2) == true && utilities.isValidTime(EndTime1) == true && utilities.isValidTime(EndTime2) == true)) {

            // Get info about the semester
            offeredCourseJsonText = getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
            CourseOFBatches_JsonObj = getJsonObj(offeredCourseJsonText); //all course name of that batch

            // Get info of batches according to the course of the semester
            runningCourseJsonData = getJsonText("SELECT * FROM `information` WHERE attribute ='runningCourseData'");
            assignedBatchOfThatCourse_JsonObj = getJsonObj(runningCourseJsonData);//batch name that are assigned to that course

            totalCourse_FOR_LOOP_TRAVERSAL = new ArrayList<String>(assignedBatchOfThatCourse_JsonObj.keySet());  //keyset-- total course count
            batchNo = new ArrayList<String>(CourseOFBatches_JsonObj.keySet());//keyset of jar array --- the batch no in the List

            while (totalCourse_FOR_LOOP_TRAVERSAL.size() > 0) {

                if(offDayList.contains(examStartDay) == true || examStartDay.getDayOfWeek().toString() == "THURSDAY" || examStartDay.getDayOfWeek().toString() == "FRIDAY" ){
                    incrementDayAction();
                }
                avaialable=true;
                otherbatchExamOnThatDay=false;
                assignedCourse = (JSONArray) CourseOFBatches_JsonObj.get(batchNo.get(Batchcount));

                if(assignedCourse.size()>0){
                    CheckAvailable(0);
                }else{ // if all courses exam are taken for that batch
                    avaialable=false;
                }


                if(otherbatchExamOnThatDay==true && avaialable ==false){
                    tempCourse = String.valueOf(assignedCourse.get(0));
                    for (int i = 1; i < assignedCourse.size(); i++) {
                        CheckAvailable(i);
                        if(avaialable==true){
                            break;
                        }
                    }
                }

               if(avaialable==true){
                       SetRoutine();
                       Room_Shift_Change();
               }
               if(avaialable==false) { // whole batch k traverse krar por o jdi avaiable na hoy oi course assign kra, because of all batch are giving eexam or there is no exam for batch
                   if(batchNo.size() ==(Batchcount+1)){
                       incrementDayAction();
                   }
               }
               IncrementBatch();
               IncrementExamDaysIf_all_batch_exam_taken_Or_Off_day();

                if (totalCourse_FOR_LOOP_TRAVERSAL.size() == 0) {
                    error.setText("Completed");
                    create.setDisable(true);
                    gadd.setDisable(true);
                    break;
                }
            }

        } else{
            error.setText("Input Format or data is not correctly given.Try Again !!");
        }

    }

    private  void CheckAvailable(int count){
        avaialable=true;
        tempCourse = String.valueOf(assignedCourse.get(count));
        assignedBatch = (JSONArray) assignedBatchOfThatCourse_JsonObj.get(tempCourse); // batch data collect of that course


        //if any of the batch are assigned on that day, it won't go
        for (int i = 0; i < assignedBatch.size(); i++) {
            if(takenExamBatchShift.contains(assignedBatch.get(i).toString())){
                avaialable=false;
                otherbatchExamOnThatDay=true;
                break;
            }
        }
    }


    private  void SetRoutine(){
        String batches ="";

        //add to taken exam
        for (int i = 0; i < assignedBatch.size(); i++) {
            if(!takenExamBatchShift.contains(assignedBatch.get(i).toString())){
                takenExamBatchShift.add(assignedBatch.get(i).toString());
            }

            batches+=assignedBatch.get(i).toString();
            batches+=" ";

            //delete from the batch's courses
            assignedCourse = (JSONArray) CourseOFBatches_JsonObj.get(assignedBatch.get(i).toString());

            assignedCourse.remove(tempCourse);

        }


        examScheduleTableObj= new ExamScheduleTableClass(count,examStartDay,(courseinfo.get(tempCourse) + "["+batches+"]"),fixedTime,matcher.group(fixedRoom));
        ESchedule_table.getItems().add(examScheduleTableObj);

        totalCourse_FOR_LOOP_TRAVERSAL.remove(tempCourse);  //remove for loop traversal ,it will descrease size to end
        count++;


    }

    private  void Room_Shift_Change() {
        //room changing
        if (fixedRoom == 3) {
            fixedRoom = 1;  //if all room are occupied than set the first room
            fixedShift++;   //increase shift to go next shift means ++, and next shift occupied than go to shift 1
        } else {
            fixedRoom++;
        }

        if (fixedShift == 1) {
            setTime();
        } else if (fixedShift == 2) {
            setTime();
            if (fixedRoom == 3) {
                fixedShift = 1;
            }
        }
    }
    private  void IncrementExamDaysIf_all_batch_exam_taken_Or_Off_day() {
         //date changing if exam all are taken for all batch
        if (takenExamBatchShift.size() == batchNo.size() ) {
            incrementDayAction();

        }
    }
    private  void setTime(){
        if (fixedShift == 1) {
            fixedTime = "";
            fixedTime += StartTime1 + "-" + EndTime1;
        } else if (fixedShift == 2) {
            fixedTime = "";
            fixedTime += StartTime2 + "-" + EndTime2;
        }
    }
    private  void  incrementDayAction(){
        fixedRoom=1;
        fixedShift=1;
        setTime();
        takenExamBatchShift.clear();
        takenExamBatchShift1.clear();
        takenExamBatchShift2.clear();
        examStartDay = examStartDay.plusDays(1);
        if(offDayList.contains(examStartDay) == true || examStartDay.getDayOfWeek().toString() == "THURSDAY" || examStartDay.getDayOfWeek().toString() == "FRIDAY" ){
            incrementDayAction();
        }
    }
    private  void IncrementBatch( ){

        if (Batchcount == (batchNo.size() - 1)) {
            Batchcount = 0;
        } else {
            Batchcount++;
        }
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
                Udate.setValue(null);
                Ucourse.setText("");
                Utime.setText("");
                UroomNo.setText("");
                NoOfSelectedExam.setText("");
                break;
            }

        }


    }

    private String removeLastChar(String s)
    {

        return s.substring(0, s.length() - 1);
    }

    @FXML
    void RoutinePublishButton(ActionEvent event) throws SQLException, IOException {
        publish.setDisable(true);
        ObservableList<ExamScheduleTableClass> currentTableData = ESchedule_table.getItems();


        String order = "INSERT INTO `Routine` (`Date`, `courseName`, `times`, `room`) VALUES ";
        for (ExamScheduleTableClass examScheduleTable:  currentTableData) {

            order += "('"+examScheduleTable.getExamDate().toString()+"', '"+examScheduleTable.getCourseName().toString()+"', '"+examScheduleTable.getExamTime().toString()+"', '"+examScheduleTable.getExamRoom().toString()+"') ,";



        }
        order = removeLastChar(order);
        s.executeUpdate(order);

        Parent fxml2 = FXMLLoader.load(getClass().getResource("View_Exam_Routine.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    HashMap<String, String> courseinfo;
    @FXML
    void initialize() throws SQLException {
        gc();
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


       courseinfo = new HashMap<String, String>();


        ResultSet r = s.executeQuery("SELECT * FROM `courseinfo`");
        while (r.next()) {
            courseinfo.put(r.getString("CourseCode"),r.getString("CourseName"));

        }




        NoOfSelectedExam.setEditable(false);
        col_offday.setCellValueFactory(new PropertyValueFactory<>("OffDay"));
        Col_Exam_No.setCellValueFactory(new PropertyValueFactory<>("no"));
        Col_Exam_Date.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        Col_Course_Name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        Col_Time.setCellValueFactory(new PropertyValueFactory<>("examTime"));
        Col_Room.setCellValueFactory(new PropertyValueFactory<>("ExamRoom"));


    }

}
