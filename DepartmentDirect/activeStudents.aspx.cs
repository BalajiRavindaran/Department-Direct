using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Web.UI.WebControls;
using Newtonsoft.Json; // Ensure Newtonsoft.Json is installed via NuGet

namespace DepartmentDirect
{
    public partial class activeStudents : System.Web.UI.Page
    {
        private static readonly HttpClient client = new HttpClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadStudents();
            }
        }

        private void LoadStudents()
        {
            try
            {
                // Retrieve departmentID from session
                string departmentID = Session["DepartmentId"] as string;

                // Define the API endpoint
                string apiUrl = "http://3.128.202.148:9090/departmentdirect/qa/listallstudent";

                // Make the API GET request synchronously
                HttpResponseMessage response = client.GetAsync(apiUrl).Result;

                // Ensure the request was successful
                response.EnsureSuccessStatusCode();

                // Read the JSON response
                string jsonResponse = response.Content.ReadAsStringAsync().Result;

                // Deserialize the JSON response into a list of dynamic objects
                var jsonData = JsonConvert.DeserializeObject<List<dynamic>>(jsonResponse);

                // Transform the data into a list of Student objects, filtered by departmentID
                var students = new List<Student>();
                foreach (var item in jsonData)
                {
                    // Check if the current item's departmentID matches the session's departmentID
                    if (item.departmentID == departmentID)
                    {
                        students.Add(new Student
                        {
                            StudentID = item.studentID,
                            Name = item.studentName
                        });
                    }
                }

                // Bind the data to the GridView
                StudentGridView.DataSource = students;
                StudentGridView.DataBind();
                NoStudentsLabel.Visible = students.Count == 0; // Show label if no students
            }
            catch (Exception ex)
            {
                // Handle exceptions
                Response.Write($"<script>alert('Error loading student data: {ex.Message}');</script>");
            }
        }

        protected void StudentGridView_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "ViewDetails")
            {
                string studentId = e.CommandArgument.ToString();
                // Redirect to another page with the selected student ID
                Session["StudentId"] = studentId;
                Response.Redirect("chatStudent.aspx");
            }
        }
    }

    public class Student
    {
        public string StudentID { get; set; }
        public string Name { get; set; }
    }
}
