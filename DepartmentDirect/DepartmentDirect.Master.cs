using System;
using System.Web.UI;

namespace DepartmentDirect
{
    public partial class DepartmentDirect : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (Session["Role"] == null || !Session["Login"].Equals("True"))
                {
                    // User is not logged in
                    LinkButton4.Visible = true; // ViewDepartment
                    LinkButton1.Visible = true;  // Login
                    LinkButton2.Visible = true;  // SignUp
                    LinkButton3.Visible = false; // LogOut
                    LinkButton7.Visible = false; // HelloUser

                    LinkButton6.Visible = true;  // AdminLogin
                    LinkButton11.Visible = false; // ArtistLogin
                    LinkButton12.Visible = false; // DepartmentLogin
                }
                else if (Session["Role"].Equals("Student") && Session["Login"].Equals("True"))
                {
                    // User is a logged-in student
                    LinkButton4.Visible = true;  // ViewDepartment
                    LinkButton1.Visible = false; // Login
                    LinkButton2.Visible = false; // SignUp
                    LinkButton3.Visible = true;  // LogOut
                    LinkButton7.Visible = true;  // HelloUser
                    LinkButton7.Text = "Hello " + Session["FullName"].ToString();

                    LinkButton6.Visible = false; // AdminLogin
                    LinkButton11.Visible = false; // ArtistLogin
                    LinkButton12.Visible = false; // DepartmentLogin
                }
            }
            catch (Exception exception)
            {
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
            Session["Role"] = "";
            Session["Login"] = "";

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
    }
}
