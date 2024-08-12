using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics.Tracing;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class adminLogin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (validations())
            {

                string adminId = TextBox1.Text;
                string password = TextBox2.Text;
                string role = "Staff";

                if (adminId == "20")
                {
                    role = "Staff";
                } 
                else if (adminId == "21")
                {
                    role = "Admin";
                }


                using (HttpClient client = new HttpClient())
                {
                    try
                    {
                        // Construct the URL with query parameters
                        string url = $"http://3.128.202.148:9090/departmentdirect/users/loginstaff?ID={adminId}&Password={password}&Role={role}";

                        // Make the GET request
                        HttpResponseMessage response = client.GetAsync(url).Result; // Blocking call here
                        string responseString = response.Content.ReadAsStringAsync().Result; // Blocking call here

                        if (response.IsSuccessStatusCode)
                        {
                            // Deserialize the JSON response
                            var user = JsonConvert.DeserializeObject<Dictionary<string, string>>(responseString);

                            Session["FullName"] = user["Role"];
                            Session["Role"] = user["Role"];
                            Session["Login"] = true;
                            Session["AdminId"] = adminId;

                            // Provide feedback
                            Label1.Text = "Login successful! Redirecting to Home";
                            Label1.ForeColor = System.Drawing.Color.Green;
                            Label1.Visible = true;

                            // Redirect to home page
                            Response.Redirect("home.aspx");
                        }
                        else
                        {
                            Label1.Text = "Login failed: Invalid Credentials";
                            Label1.ForeColor = System.Drawing.Color.Red;
                            Label1.Visible = true;
                        }
                    }
                    catch (Exception ex)
                    {
                        Label1.Text = "Login failed: " + ex.Message;
                        Label1.Visible = true;
                    }
                }
            }
        }

        bool validations()
        {
            if ((TextBox1.Text.Trim() == null || TextBox1.Text.Trim() == "") || (TextBox2.Text.Trim() == null || TextBox2.Text.Trim() == ""))
            {
                Label1.Text = "Login Credentials cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                Label1.Visible = true;
                return false;
            }
            else
            {
                return true;
            }
        }
    }
}