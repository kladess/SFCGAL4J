package edu.pnu.stemlab.sfcgal4j;

import java.util.ArrayList;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = {"cpp/SFPolygon.cpp"}, link = {"SFCGAL"})
public class SFPolygon extends SFSurface {
        static {
                Loader.load();
        }

        public SFPolygon() {
                allocate();
        }

        public SFPolygon(ArrayList<SFLineString> rings) {
                PointerVector vector = new PointerVector(rings.size());

                for (int i = 0; i < rings.size(); i++) {
                        vector.get(i).put(rings.get(i));
                }

                allocate(vector);
        }

        public SFPolygon(SFLineString exteriorRing) {
                allocate(exteriorRing);
        }

        public SFPolygon(SFTriangle triangle) {
                allocate(triangle);
        }

        public SFPolygon(Pointer p) {
                super(p);
        }

        private native void allocate();

        private native void allocate(@ByRef PointerVector p);

        private native void allocate(@ByRef SFLineString exteriorRing);

        private native void allocate(@ByRef SFTriangle triangle);

        @Name("operator=")
        public native @ByRef SFPolygon assign(@ByRef SFPolygon polygon);

        public native SFPolygon clone();

        public native @StdString String geometryType();

        public native int geometryTypeId();

        public native int dimension();

        public native int coordinateDimension();

        public native @Cast("bool") boolean isEmpty();

        public native @Cast("bool") boolean is3D();

        public native @Cast("bool") boolean isMeasured();

        public native @Cast("bool") boolean isCounterClockWiseOriented();

        public native void reverse();

        public native @ByRef SFLineString exteriorRing();

        public native void setExteriorRing(@ByRef SFLineString exteriorRing);

        public native @Cast("bool") boolean hasInteriorRings();

        public native @Cast("size_t") int numInteriorRings();

        public native @ByRef SFLineString interiorRingN(@Cast("size_t") int n);

        public native @Cast("size_t") int numRings();

        public native @ByRef SFLineString ringN(@Cast("size_t") int n);

        public native void addInteriorRing(@ByRef SFLineString ls);

        public native void addRing(@ByRef SFLineString ls);

}
