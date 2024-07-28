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
                new Department { Id = 1, Name = "Electrical Engineering", PreviousConversations = 85 },
                new Department { Id = 2, Name = "Computer Engineering", PreviousConversations = 20 },
                new Department { Id = 3, Name = "Co-Op Education", PreviousConversations = 70 },
                new Department { Id = 4, Name = "Q&A General", PreviousConversations = 50 },
                
            };

            departmentRepeater.DataSource = departments;
            departmentRepeater.DataBind();
        }

        protected void departmentRepeater_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "GoToChat")
            {
                string departmentId = e.CommandArgument.ToString();
                //Response.Redirect($"departmentChat.aspx?departmentId={departmentId}");
                Response.Redirect("chatFaculty.aspx");
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
