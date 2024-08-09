using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class activeStudents : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadStudents();
            }
        }

        private void LoadStudents()
        {
            // Sample data for demonstration
            var students = new List<Student>
            {
                new Student { StudentID = "1001", Name = "John Doe" },
                new Student { StudentID = "1002", Name = "Jane Smith" },
                new Student { StudentID = "1003", Name = "Alice Johnson" },
                new Student { StudentID = "1004", Name = "Bob Brown" }
            };

            StudentGridView.DataSource = students;
            StudentGridView.DataBind();
        }

        protected void StudentGridView_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "ViewDetails")
            {
                string studentId = e.CommandArgument.ToString();
                // Redirect to another page with the selected student ID
                Response.Redirect($"studentDetails.aspx?studentId={studentId}");
            }
        }
    }

    public class Student
    {
        public string StudentID { get; set; }
        public string Name { get; set; }
    }
}
