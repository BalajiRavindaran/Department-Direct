using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class resetPassword : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (validations())
            {
                string studentID = TextBox1.Text;
                string email = TextBox2.Text;
                string oldPassword = TextBox4.Text;
                string newPassword = TextBox3.Text;

                using (HttpClient client = new HttpClient())
                {
                    using (var formData = new MultipartFormDataContent())
                    {
                        formData.Add(new StringContent(studentID), "StudentID"); // Ensure this matches API expectations
                        formData.Add(new StringContent(email), "Email");
                        formData.Add(new StringContent(newPassword), "NewPassword");
                        formData.Add(new StringContent(oldPassword), "Password"); // Ensure this matches API expectations
                        

                        try
                        {
                            // Synchronous wait for the async operation
                            HttpResponseMessage response = client.PostAsync("http://3.128.202.148:9090/departmentdirect/users/changepass", formData).Result;
                            string responseString = response.Content.ReadAsStringAsync().Result;

                            if (response.IsSuccessStatusCode)
                            {
                                Label1.Text = "Password Reset Successful";
                                Label1.ForeColor = System.Drawing.Color.Green;
                                Label1.Visible = true;
                            }
                            else
                            {
                                Label1.Text = "Reset failed: " + responseString;
                                Label1.ForeColor = System.Drawing.Color.Red;
                                Label1.Visible = true;
                            }
                        }
                        catch (Exception ex)
                        {
                            Label1.Text = "Reset failed: " + ex.Message;
                            Label1.Visible = true;
                        }
                    }
                }
            }
        }



        bool validations()
        {
            if (string.IsNullOrWhiteSpace(TextBox1.Text) || string.IsNullOrWhiteSpace(TextBox2.Text) || string.IsNullOrWhiteSpace(TextBox3.Text))
            {
                Label1.Text = "Reset Password Credentials cannot be empty";
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
