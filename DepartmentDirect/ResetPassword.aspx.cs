using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net.Http;
using Newtonsoft.Json;

namespace DepartmentDirect
{
    public partial class ResetPassword : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected async void Button1_Click(object sender, EventArgs e)
        {
            string studentId = TextBox1.Text;
            string password = TextBox2.Text;
            string newpassword_1 = TextBox3.Text;
            string newpassword_2 = TextBox4.Text;


            using (HttpClient client = new HttpClient())
            {
                try
                {
                    // Construct the URL with query parameters
                    string url = $"http://18.226.133.209:9090/departmentdirect/users/login?studentid={studentId}&password={password}";

                    // Make the GET request
                    HttpResponseMessage response = await client.GetAsync(url);
                    string responseString = await response.Content.ReadAsStringAsync();

                    if (response.IsSuccessStatusCode)
                    {
                        if (newpassword_1 == newpassword_2)
                        {
                            //update the password in the database


                            Label1.Text = "Reset Successful! Login to Access";
                            Label1.ForeColor = System.Drawing.Color.Green;
                            Label1.Visible = true;


                            Response.Redirect("studentLogin.aspx");
                        }


                        else
                        {
                            Label1.Text = "Reset Failed! New passwords must be same!";
                            Label1.ForeColor = System.Drawing.Color.Red;
                            Label1.Visible = true;
                            // Refresh the page after a short delay
                            ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 1200);", true);

                        }

                      
                    }
                    else
                    {
                        Label1.Text = "Reset failed! Password doesn't match ID";
                        Label1.ForeColor = System.Drawing.Color.Red;
                        Label1.Visible = true;
                        // Refresh the page after a short delay
                        ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 1200);", true);
                    }
                }
                catch (Exception ex)
                {
                    Label1.Text = "Reset failed: " + ex.Message;
                    Label1.ForeColor = System.Drawing.Color.Red;
                    Label1.Visible = true;

                    // Refresh the page after a short delay
                    ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 10200);", true);
                }
            }








            //Label1.Text = "Reset Successful! Login to Access";
            //Label1.ForeColor = System.Drawing.Color.Green;
            //Label1.Visible = true;

            //string script = "setTimeout(function() { window.location = 'studentLogin.aspx'; }, 3000);"; // 1500 milliseconds = 1.5 seconds
            //ClientScript.RegisterStartupScript(this.GetType(), "Redirect", script, true);
            

        }
    }
}