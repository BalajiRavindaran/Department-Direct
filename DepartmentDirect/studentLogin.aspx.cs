using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Web.UI;
using Newtonsoft.Json;

namespace DepartmentDirect
{
    public partial class studentLogin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (validations())
            {

                string studentId = TextBox1.Text;
                string password = TextBox2.Text;

                using (HttpClient client = new HttpClient())
                {
                    try
                    {
                        // Construct the URL with query parameters
                        string url = $"http://3.128.202.148:9090/departmentdirect/users/login?studentid={studentId}&password={password}";

                        // Make the GET request
                        HttpResponseMessage response = client.GetAsync(url).Result; // Blocking call here
                        string responseString = response.Content.ReadAsStringAsync().Result; // Blocking call here

                        if (response.IsSuccessStatusCode)
                        {
                            // Deserialize the JSON response
                            var user = JsonConvert.DeserializeObject<Dictionary<string, string>>(responseString);

                            // Set session variables
                            Session["StudentId"] = user["studentId"];
                            Session["FullName"] = user["fullname"];
                            Session["Email"] = user["email"];
                            Session["DateOfBirth"] = user["dateOfBirth"];
                            Session["Notifications"] = user["notifications"];
                            Session["Role"] = "Student";
                            Session["Login"] = true;

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
