using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class ResetPassword : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void Button1_Click(object sender, EventArgs e)
        {
            Label1.Text = "Reset Successful! Login to Access";
            Label1.ForeColor = System.Drawing.Color.Green;
            Label1.Visible = true;

            string script = "setTimeout(function() { window.location = 'studentLogin.aspx'; }, 3000);"; // 1500 milliseconds = 1.5 seconds
            ClientScript.RegisterStartupScript(this.GetType(), "Redirect", script, true);
            //Response.Redirect("studentLogin.aspx");

        }
    }
}