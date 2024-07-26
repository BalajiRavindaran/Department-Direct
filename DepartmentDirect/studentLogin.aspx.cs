using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.UI;
using Newtonsoft.Json;

namespace DepartmentDirect
{
    public partial class studentLogin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected async void Button1_Click(object sender, EventArgs e)
        {
            string studentId = TextBox1.Text;
            string password = TextBox2.Text;

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
                        Label1.Text = "Login successful! Redirecting to Home";
                        Label1.ForeColor = System.Drawing.Color.Green;
                        Label1.Visible = true;

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
}
