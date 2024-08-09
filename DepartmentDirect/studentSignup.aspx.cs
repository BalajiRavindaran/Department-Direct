using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class studentSignup : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected async void Button1_Click(object sender, EventArgs e)
        {
            if (Validation())
            {
            string fullName = TextBox1.Text;
            string dob = TextBox2.Text;
            string email = TextBox3.Text;
            string notifications = DropDownList2.SelectedValue;
            string studentID = TextBox8.Text;
            string password = TextBox9.Text;

            using (HttpClient client = new HttpClient())
            {
                using (var formData = new MultipartFormDataContent())
                {
                    formData.Add(new StringContent(fullName), "Fullname");
                    formData.Add(new StringContent(dob), "DateOfBirth");
                    formData.Add(new StringContent(email), "Email");
                    formData.Add(new StringContent(notifications), "Notifications");
                    formData.Add(new StringContent(studentID), "StudentID");
                    formData.Add(new StringContent(password), "Password");

                    try
                    {
                        HttpResponseMessage response = await client.PostAsync("http://3.128.202.148:9090/departmentdirect/users/create", formData);
                        string responseString = await response.Content.ReadAsStringAsync();

                        if (response.IsSuccessStatusCode)
                        {
                            Label1.Text = "Signup successful! Login to Access";
                            Label1.ForeColor = System.Drawing.Color.Green;
                            Label1.Visible = true;
                        }
                        else
                        {
                            Label1.Text = "Signup failed: " + responseString;
                            Label1.ForeColor = System.Drawing.Color.Red;
                            Label1.Visible = true;
                        }
                    }
                    catch (Exception ex)
                    {
                        Label1.Text = "Signup failed: " + ex.Message;
                        Label1.Visible = true;
                    }
                }
            }
            }
        }

        bool Validation()
        {
            bool containsInt = TextBox9.Text.Trim().Any(char.IsDigit);
            var specialCharacters = new List<string> { "~", "'", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "{", "}", "[", "]", "|", "/", "?", "<", ">" };
            bool containsSpecialCharacters = specialCharacters.Any(TextBox9.Text.Trim().Contains);


            if (TextBox1.Text.Trim() == null || TextBox1.Text.Trim() == "")
            {
                Label1.Visible = true;
                Label1.Text = "Full Name cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (TextBox2.Text.Trim() == null || TextBox2.Text.Trim() == "")
            {
                Label1.Visible = true;
                Label1.Text = "Date Of Birth cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (TextBox3.Text.Trim() == null || TextBox3.Text.Trim() == "")
            {
                Label1.Visible = true;
                Label1.Text = "Email cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (DropDownList2.SelectedItem.Value == "Select")
            {
                Label1.Visible = true;
                Label1.Text = "Select your appropriate notification preference";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (TextBox8.Text.Trim() == null || TextBox8.Text.Trim() == "")
            {
                Label1.Visible = true;
                Label1.Text = "Student ID cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (TextBox9.Text.Trim() == null || TextBox9.Text.Trim() == "")
            {
                Label1.Visible = true;
                Label1.Text = "Password cannot be empty";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else if (TextBox9.Text.Trim().Length < 8 || containsInt == false || containsSpecialCharacters == false)
            {
                Label1.Visible = true;
                Label1.Text = "Password too weak";
                Label1.ForeColor = System.Drawing.Color.Red;
                return false;
            }
            else
            {
                return true;
            }
        }
    }

 }