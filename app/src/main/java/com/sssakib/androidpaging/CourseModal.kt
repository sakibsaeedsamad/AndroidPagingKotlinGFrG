class CourseModal
{
    var courseName : String? = null
    var courseModes : String? = null
    var courseTracks : String? = null
    var courseImg : String? = null

    constructor(
        courseName: String?,
        courseModes: String?,
        courseTracks: String?,
        courseImg: String?
    ) {
        this.courseName = courseName
        this.courseModes = courseModes
        this.courseTracks = courseTracks
        this.courseImg = courseImg
    }

}