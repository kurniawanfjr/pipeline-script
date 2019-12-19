#!/usr/bin/env groovy
import java.text.SimpleDateFormat

static def getDate2WeeksAgo() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date2WeeksAgo = new Date().minus(14);
    sdf.format(date2WeeksAgo)
}

def validateCreatedFileWithin2Weeks() {
    String date2WeeksAgo = getDate2WeeksAgo()
    println 'Date 2 Weeks ago: ' + date2WeeksAgo
    sh '''
        if [[ $(gdrive list -m 100 -q "createdTime < '${date2WeeksAgo}'" | awk '/MB/{print}') ]]
        then
            echo "There are several files above 2 weeks ago"
            gdrive delete $(gdrive list -m 100 -q "createdTime < ''${date2WeeksAgo}'" | awk '/MB/{print $1}')
        else
            echo "There's nothing files above 2 weeks ago"
    '''
}

return this
