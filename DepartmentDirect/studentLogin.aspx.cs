using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI;
using System.Web.UI.WebControls;
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

            var loginInfo = new
            {
                StudentID = studentId,
                Password = password
            };

            string json = JsonConvert.SerializeObject(loginInfo);
            StringContent content = new StringContent(json, Encoding.UTF8, "application/json");

            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage response = await client.PostAsync("https://d5180df3-2e94-4105-8a29-11c592d37705-00-1b7bcoscpivxb.janeway.replit.dev/api/validate", content);
                string responseString = await response.Content.ReadAsStringAsync();

                dynamic result = JsonConvert.DeserializeObject(responseString);
                bool isValid = result.isValid;

                if (isValid)
                {
                    // Redirect to a different page or show a success message
                    Response.Redirect("home.aspx");
                }
                else
                {
                    Label1.Text = "Invalid login credentials";
                    Label1.Visible = true;
                }
            }
        }
    }
}
