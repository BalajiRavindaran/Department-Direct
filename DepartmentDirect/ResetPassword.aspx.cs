using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net.Http;
using Newtonsoft.Json;
using System.Configuration;
using System.Data.SqlClient;
using System.Threading.Tasks;


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
                    string url = $"http://3.128.202.148:9090/departmentdirect/users/login?studentid={studentId}&password={password}";

                    // Make the GET request
                    HttpResponseMessage response = await client.GetAsync(url);
                    string responseString = await response.Content.ReadAsStringAsync();

                    if (response.IsSuccessStatusCode)
                    {
                        if (newpassword_1 == newpassword_2)
                        {
                            if (newpassword_1.Length < 8)
                            {
                                Label1.Text = "Reset Failed! Password length must be at least 8 characters.";
                                Label1.ForeColor = System.Drawing.Color.Red;
                                Label1.Visible = true;
                                ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 2000);", true);
                            }
                            else 
                            {
                                //update the password in the database
                                bool success = await UpdatePasswordAsync(studentId, newpassword_1);
                                if (success)
                                {
                                    Label1.Text = "Reset Successful! Login to Access";
                                    Label1.ForeColor = System.Drawing.Color.Green;
                                    Label1.Visible = true;

                                    Response.Redirect("studentLogin.aspx");
                                }
                                
                            }
                            

                            
                        }


                        else
                        {
                            Label1.Text = "Reset Failed! New passwords must be same!";
                            Label1.ForeColor = System.Drawing.Color.Red;
                            Label1.Visible = true;
                            // Refresh the page after a short delay
                            ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 2000);", true);

                        }


                    }
                    else
                    {
                        Label1.Text = "Reset failed! Password or ID is invalid";
                        Label1.ForeColor = System.Drawing.Color.Red;
                        Label1.Visible = true;
                        // Refresh the page after a short delay
                        ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 2000);", true);
                    }
                }
                catch (Exception ex)
                {
                    Label1.Text = "Reset failed: " + ex.Message;
                    Label1.ForeColor = System.Drawing.Color.Red;
                    Label1.Visible = true;

                    // Refresh the page after a short delay
                    ClientScript.RegisterStartupScript(this.GetType(), "refresh", "setTimeout(function(){ window.location = 'ResetPassword.aspx'; }, 2000);", true);
                }
            }



        }
        private async Task<bool> UpdatePasswordAsync(string studentId, string newPassword)
        {
            using (HttpClient client = new HttpClient())
            {
                var formData = new MultipartFormDataContent();
                formData.Add(new StringContent(studentId), "StudentID");
                formData.Add(new StringContent(newPassword), "NewPassword");

                try
                {
                    string url = "http://3.128.202.148:9090/departmentdirect/users/updatePassword";
                    HttpResponseMessage response = await client.PostAsync(url, formData);

                    if (response.IsSuccessStatusCode)
                    {
                        return true;
                    }
                    else
                    {
                        string responseString = await response.Content.ReadAsStringAsync();
                        Label1.Text = "Error updating password: " + responseString;
                        Label1.ForeColor = System.Drawing.Color.Red;
                        Label1.Visible = true;
                        return false;
                    }
                }
                catch (Exception ex)
                {
                    Label1.Text = "Error updating password: " + ex.Message;
                    Label1.ForeColor = System.Drawing.Color.Red;
                    Label1.Visible = true;
                    return false;
                }
            }
        }



    }

}