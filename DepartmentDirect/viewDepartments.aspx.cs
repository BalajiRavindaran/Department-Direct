using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class viewDepartments : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                PopulateDepartments();
            }
        }

        private void PopulateDepartments()
        {
            // Sample data, replace with actual data fetching logic
            var departments = new List<Department>
            {
                new Department { Id = 1, Name = "Electrical Engineering" },
                new Department { Id = 2, Name = "Computer Engineering"},
                new Department { Id = 3, Name = "Co-Op Education" },
                new Department { Id = 4, Name = "General Questions" },
                
            };

            departmentRepeater.DataSource = departments;
            departmentRepeater.DataBind();
        }

        protected void departmentRepeater_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string role = Session["Role"] as string;

            if (role == "Student")
            {
                if (e.CommandName == "GoToChat")
                {
                    string departmentId = e.CommandArgument.ToString();

                    // Set the session variable for departmentId
                    Session["DepartmentId"] = departmentId;

                    // Redirect to chatStudent.aspx
                    Response.Redirect("chatFaculty.aspx");
                }
            }
            else if (role == "Staff")
            {
                if (e.CommandName == "GoToChat")
                {
                    string departmentId = e.CommandArgument.ToString();

                    // Set the session variable for departmentId
                    Session["DepartmentId"] = departmentId;

                    // Redirect to chatFaculty.aspx
                    Response.Redirect("activeStudents.aspx");
                }
            }
        }
    }

    public class Department
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int PreviousConversations { get; set; }
    }
}
