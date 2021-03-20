# tutorial-relatedToImage






![alt text](http://1.bp.blogspot.com/-9UCoqk4eOYs/UXOXi4Gqy5I/AAAAAAAAAG4/A8z6ociZjPY/s1600/android.png)
[http://1.bp.blogspot.com/-9UCoqk4eOYs/UXOXi4Gqy5I/AAAAAAAAAG4/A8z6ociZjPY/s1600/android.png]


*onMeasure*
call from Measure
Measure the view and its content to determine the measured width and the measured height.
참고로 자식 View size 측정을 위해서 여러번 호출될 수 있다.

*onLayout*
Called from layout when this view should assign a size and position to each of its children.

*onDraw*
Finally draw canvas with paint and variety shapes

*invalidate*
will call onDraw again
only use in UI Thread. using postInvalidate() in non Thread is possible 
