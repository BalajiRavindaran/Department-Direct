using System;
using System.Web.UI;

namespace DepartmentDirect
{
    public partial class DepartmentDirect : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                try
                {
                    // Check session values
                    bool isLoggedIn = Session["Login"] != null && (bool)Session["Login"];
                    string role = Session["Role"] as string;

                    if (!isLoggedIn)
                    {
                        // User is not logged in
                        LinkButton4.Visible = false; // ViewDepartment
                        LinkButton1.Visible = true;  // Login
                        LinkButton2.Visible = true;  // SignUp
                        LinkButton3.Visible = false; // LogOut
                        LinkButton7.Visible = false; // HelloUser

                        LinkButton6.Visible = true;  // AdminLogin
                        LinkButton11.Visible = false; // Analytics
                        LinkButton12.Visible = false; // DepartmentLogin
                    }
                    else if (role == "Student")
                    {
                        // User is a logged-in student
                        LinkButton4.Visible = true;  // ViewDepartment
                        LinkButton1.Visible = false; // Login
                        LinkButton2.Visible = false; // SignUp
                        LinkButton3.Visible = true;  // LogOut
                        LinkButton7.Visible = true;  // HelloUser
                        LinkButton7.Text = "Hello " + (Session["FullName"] as string);

                        LinkButton6.Visible = true; // AdminLogin
                        LinkButton11.Visible = false; // Analytics
                        LinkButton12.Visible = false; // DepartmentLogin
                    }
                    else if (role == "Admin")
                    {
                        // User is a logged-in student
                        LinkButton4.Visible = false;  // ViewDepartment
                        LinkButton1.Visible = false; // Login
                        LinkButton2.Visible = false; // SignUp
                        LinkButton3.Visible = true;  // LogOut
                        LinkButton7.Visible = true;  // HelloUser
                        LinkButton7.Text = "Hello " + (role);

                        LinkButton6.Visible = false; // AdminLogin
                        LinkButton5.Visible = true; // Analytics
                        LinkButton11.Visible = false; // DepartmentLogin
                        LinkButton12.Visible = true; // DepartmentLogin
                    }
                    else if (role == "Staff")
                    {
                        // User is a logged-in student
                        LinkButton4.Visible = false;  // ViewDepartment
                        LinkButton1.Visible = false; // Login
                        LinkButton2.Visible = false; // SignUp
                        LinkButton3.Visible = true;  // LogOut
                        LinkButton7.Visible = true;  // HelloUser
                        LinkButton7.Text = "Hello " + (role);

                        LinkButton6.Visible = false; // AdminLogin
                        LinkButton11.Visible = true; // Questions
                        LinkButton12.Visible = false; // DepartmentLogin
                        LinkButton12.Visible = false; // DepartmentLogin
                    }
                }
                catch (Exception exception)
                {
                    // Handle the exception (logging, displaying an error message, etc.)
                    Console.WriteLine("Error accessing session variables: " + exception.Message);
                }
            }
        }

        protected void LinkButton4_Click(object sender, EventArgs e)
        {
            // ViewDepartment logic
            Response.Redirect("viewDepartments.aspx");
        }

        protected void LinkButton1_Click(object sender, EventArgs e)
        {
            Response.Redirect("studentLogin.aspx");
        }

        protected void LinkButton2_Click(object sender, EventArgs e)
        {
            Response.Redirect("studentSignUp.aspx");
        }

        protected void LinkButton3_Click(object sender, EventArgs e)
        {
            // Clear session variables
            Session.Clear();

            LinkButton4.Visible = false; // ViewDepartment
            LinkButton1.Visible = true;  // Login
            LinkButton2.Visible = true;  // SignUp
            LinkButton3.Visible = false; // LogOut
            LinkButton7.Visible = false; // HelloUser

            LinkButton6.Visible = true;  // AdminLogin
            LinkButton11.Visible = false; // ArtistLogin
            LinkButton12.Visible = false; // DepartmentLogin

            Response.Redirect("home.aspx");
        }

        protected void LinkButton12_Click(object sender, EventArgs e)
        {
            Response.Redirect("publishNotification.aspx");
        }

        protected void LinkButton11_Click(object sender, EventArgs e)
        {
            Response.Redirect("viewDepartments.aspx");
        }

        protected void LinkButton5_Click(object sender, EventArgs e)
        {
            Response.Redirect("adminAnalytics.aspx");
        }
    }
}
