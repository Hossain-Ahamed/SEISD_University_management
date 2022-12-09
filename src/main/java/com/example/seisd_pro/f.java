//package com.example.seisd_pro;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//
//public class Generate_Exam_Routine_Controller {
//
//    static Connection c1;
//    static Statement s;
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableColumn<?, ?> Col_Course_Name;
//
//    @FXML
//    private TableColumn<?, ?> Col_Exam_Date;
//
//    @FXML
//    private TableColumn<?, ?> Col_Room;
//
//    @FXML
//    private TableColumn<?, ?> Col_Time;
//    @FXML
//    private TableColumn<?, ?> Col_Exam_No;
//
//    @FXML
//    private TableView<ExamScheduleTableClass> ESchedule_table;
//
//    @FXML
//    private TextField End_time1;
//
//    @FXML
//    private DatePicker Exam_Offdate;
//
//    @FXML
//    private DatePicker Exam_Startdate;
//
//    @FXML
//    private TextField RoomNo;
//
//    @FXML
//    private TextField Start_time1;
//
//    @FXML
//    private TextField Start_time2;
//
//    @FXML
//    private TextField End_time2;
//
//    @FXML
//    private TextField Ucourse;
//
//    @FXML
//    private DatePicker Udate;
//
//    @FXML
//    private TextField UroomNo;
//
//    @FXML
//    private TextField Utime;
//
//    @FXML
//    private TableColumn<?, ?> col_offday;
//
//    @FXML
//    private Button create;
//
//    @FXML
//    private Label error;
//
//    @FXML
//    private Button gadd;
//
//    @FXML
//    private Button gupdate;
//
//    @FXML
//    private TextField NoOfSelectedExam;
//
//    @FXML
//    private TableView<offDayListTableClass> off_table;
//
//    @FXML
//    private Button publish;
//
//    private ArrayList<LocalDate> offDayList = new ArrayList<>(); ///off day list for skip exam on that day
//
//    @FXML
//    void OffDayAddButton(ActionEvent event) {
//
//
//        if(!offDayList.contains(Exam_Offdate.getValue())  && Exam_Offdate.getValue()!=null){
//            offDayList.add(Exam_Offdate.getValue());
//            offDayListTableClass offdayObj = new offDayListTableClass(Exam_Offdate.getValue());
//            off_table.getItems().add(offdayObj);
//            Exam_Offdate.setValue(null);
//        }
//
//
//    }
//
//    static LocalDate examStartDay;
//    String allocatedRooms,StartTime1,StartTime2,EndTime1,EndTime2;
//    @FXML
//    void CreateRoutineButton(ActionEvent event) throws SQLException {
//        error.setText("");
//
//
//
//        examStartDay= Exam_Startdate.getValue();
//        allocatedRooms = RoomNo.getText().toString().trim();
//        StartTime1 = Start_time1.getText().toString().trim();
//        StartTime2 = Start_time2.getText().toString().trim();
//        EndTime1 = End_time1.getText().toString().trim();
//        EndTime2 = End_time2.getText().toString().trim();
//
//
//        //given room for exam
//        String regex = "^(.*?)-(.*?)-(.*?)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(allocatedRooms);
//        matcher.find();  //matcher.group(1) = 111 ,matcher.group(2) = 112,matcher.group(3) = 113
//
//
//        if(examStartDay != null && matcher.matches() ==true
//                && utilities.isValidTime(StartTime1.trim()) ==true && utilities.isValidTime(StartTime2)==true
//                && utilities.isValidTime(EndTime1)==true  && utilities.isValidTime(EndTime2)==true){
//
//
//            // Get info about the semester
//            String semOrder = "SELECT * FROM `information` WHERE attribute ='courseOffer'";
//            String offeredCourseJsonText ="";
//            ResultSet r = s.executeQuery(semOrder);
//            while (r.next()) {offeredCourseJsonText=r.getString("value");}
//
//            //all course name of that batch
//            Object obj1 = JSONValue.parse(offeredCourseJsonText);
//            JSONObject CourseOFBatches_JsonObj = (JSONObject) obj1;
//            System.out.println("assigned batches course : "+CourseOFBatches_JsonObj);
//            JSONArray assignedCourse ;
//
//            // Get info of batches according to the course of the semester
//            String runningCourseOrder = "SELECT * FROM `information` WHERE attribute ='runningCourseData'";
//            String runningCourseJsonData ="";
//            ResultSet r1 = s.executeQuery(runningCourseOrder);
//            while (r1.next()) {runningCourseJsonData=r1.getString("value");}
//
//            //batch name that are assigned to that course
//            Object obj2 =JSONValue.parse(runningCourseJsonData);
//            JSONObject assignedBatchOfThatCourse_JsonObj = (JSONObject) obj2;
//            System.out.println("course ------   "+assignedBatchOfThatCourse_JsonObj);
//            JSONArray assignedBatch ;
//
//            //keyset-- total course count
//            List<String> totalCourse_FOR_LOOP_TRAVERSAL = new ArrayList<String>(assignedBatchOfThatCourse_JsonObj.keySet());
//            System.out.println(totalCourse_FOR_LOOP_TRAVERSAL.size());
//
//            //keyset of jar array --- the batch no in the List
//            List<String> batchNo = new ArrayList<String>(CourseOFBatches_JsonObj.keySet());
//
//
//
//            for ( int j =0 ;j<batchNo.size();j++){
//                assignedCourse= (JSONArray) CourseOFBatches_JsonObj.get(batchNo.get(j));
//
//                System.out.println(batchNo.get(j)+" : they are assigned for : "+assignedCourse);
//                System.out.println(assignedCourse.get(0));// first course name of that batch course -- like cse111
//
//            }
//
//            // batch data collect of that course
//            assignedBatch = (JSONArray) assignedBatchOfThatCourse_JsonObj.get("PHY113");
//
//            //delete that course from the conflict and that own batch
//            for (int i = 0; i < assignedBatch.size(); i++) {
//                assignedCourse = (JSONArray) CourseOFBatches_JsonObj.get(assignedBatch.get(i).toString());
//                assignedCourse.remove("PHY113");
//            }
//
////            while (totalCourse_FOR_LOOP_TRAVERSAL.size()>0){
////                error.setText("Processing.");
////
////                error.setText("Processing..");
////                error.setText("Processing...");
////                error.setText("Processing....");
////
////
////                if (totalCourse_FOR_LOOP_TRAVERSAL.size()==0){
////                    error.setText("Completed");
////                }
////            }
//
//            System.out.println(CourseOFBatches_JsonObj);
//            String jsonT = JSONValue.toJSONString(CourseOFBatches_JsonObj);
//            System.out.print(jsonT);
//            System.out.println(assignedBatchOfThatCourse_JsonObj);
//
//            ExamScheduleTableClass examScheduleTableObj;
//            examScheduleTableObj= new ExamScheduleTableClass(1,examStartDay,"CSE 1331","12:59","111");
//            ESchedule_table.getItems().add(examScheduleTableObj);
//            examScheduleTableObj= new ExamScheduleTableClass(2,examStartDay,"CSE 1331","12:59","111");
//            ESchedule_table.getItems().add(examScheduleTableObj);
//            examScheduleTableObj= new ExamScheduleTableClass(3,examStartDay,"CSE 1331","12:59","111");
//            ESchedule_table.getItems().add(examScheduleTableObj);
//
//        }else{
//            error.setText("Input Format or data is not correctly given.Try Again !!");
//        }
//
//
//    }
//
//    @FXML
//    void mouseEvent(MouseEvent event) {
//        ExamScheduleTableClass clickedRow = ESchedule_table.getSelectionModel().getSelectedItem();
//        NoOfSelectedExam.setText(String.valueOf(clickedRow.getNo()));
//        Udate.setValue(clickedRow.getExamDate());
//        Ucourse.setText(String.valueOf(clickedRow.getCourseName()));
//        Utime.setText(String.valueOf(clickedRow.getExamTime()));
//        UroomNo.setText(String.valueOf(clickedRow.getExamRoom()));
//
//    }
//
//
//    @FXML
//    void IndividualUpdateRoutineButton(ActionEvent event) {
//        ObservableList<ExamScheduleTableClass> currentTableData = ESchedule_table.getItems();
//        int currentExamNo = Integer.parseInt(NoOfSelectedExam.getText());
//
//        for (ExamScheduleTableClass examScheduleTable:  currentTableData) {
//            if (examScheduleTable.getNo()==currentExamNo){
//                examScheduleTable.setExamDate(Udate.getValue());
//                examScheduleTable.setCourseName(Ucourse.getText().trim());
//                examScheduleTable.setExamRoom(UroomNo.getText().trim());
//                examScheduleTable.setExamTime(Utime.getText().trim());
//
//                ESchedule_table.setItems(currentTableData);
//                ESchedule_table.refresh();
//                break;
//            }
//
//        }
//
//    }
//
//
//    @FXML
//    void RoutinePublishButton(ActionEvent event) {
//
//    }
//    @FXML
//    void initialize() {
//        this.c1 = jdbc.c1;
//        this.s = jdbc.s;
//
//
//        NoOfSelectedExam.setEditable(false);
//        col_offday.setCellValueFactory(new PropertyValueFactory<>("OffDay"));
//        Col_Exam_No.setCellValueFactory(new PropertyValueFactory<>("no"));
//        Col_Exam_Date.setCellValueFactory(new PropertyValueFactory<>("examDate"));
//        Col_Course_Name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
//        Col_Time.setCellValueFactory(new PropertyValueFactory<>("examTime"));
//        Col_Room.setCellValueFactory(new PropertyValueFactory<>("ExamRoom"));
//
//
//    }
//
//}
