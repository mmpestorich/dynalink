/*
   Copyright 2009-2012 Attila Szegedi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.dynalang.dynalink.linker;

/**
 * Optional interface that can be implemented by {@link GuardingDynamicLinker} implementations to provide
 * language-runtime specific implicit type conversion capabilities.
 *
 * @author Attila Szegedi
 * @version $Id: $
 */
public interface GuardingTypeConverterFactory {
    /**
     * Returns a method handle that receives an Object of the specified source type and returns an Object converted to
     * the specified target type. The type of the invocation is targetType(sourceType), while the type of the guard is
     * boolean(sourceType). Note that this will never be invoked for type conversions automatically handled by the
     * linker framework, namely:
     * <ul>
     * <li>when targetType is assignable from sourceType, or</li>
     * <li>for any primitive conversion, either narrowing or widening, except (byte|boolean)->char, or</li>
     * <li>for any boxing or unboxing conversion, either narrowing or widening, except (byte|boolean)->Character.</li>
     * </li>
     * An implementation can assume it is never requested to produce a converter for these conversions. As a corollary,
     * targetType will never be {@link java.lang.Object}.
     *
     * @param sourceType source type
     * @param targetType the target type.
     * @return a guarded invocation that can take an object (if it passes guard) and returns another object that is its
     * representation coerced into the target type. In case the factory is certain it is unable to handle a conversion,
     * it can return null.
     */
    public GuardedInvocation convertToType(Class<?> sourceType, Class<?> targetType);
}