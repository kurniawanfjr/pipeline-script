import java.text.SimpleDateFormat

def creation_date = "2019-10-12"

pipeline{
    agent { label 'master' }

    stages {
        stage('Verify Date') {
            steps {
                script {
                    def validateArtifacts = load "validateArtifacts.groovy"
                    validateArtifacts.removeFileMoreThan2WeeksAgo()


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
                    Date date2WeeksAgo = new Date() - 14
                    Date fileDate = sdf.parse(creation_date)

                    println("date1 : " + sdf.format(date2WeeksAgo))
                    println("date2 : " + sdf.format(fileDate))

                    if (date2WeeksAgo.after(fileDate)) {
                        println("Date1 is after Date2")
                    }

                    if (date2WeeksAgo.before(fileDate)) {
                        println("Date1 is before Date2")
                    }

                    if (date2WeeksAgo.equals(fileDate)) {
                        println("Date1 is equal Date2")
                    }
                }
            }
        }
    }
}