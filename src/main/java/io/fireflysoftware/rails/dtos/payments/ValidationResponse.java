/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.fireflysoftware.rails.dtos.payments;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ValidationResponse {
    private boolean valid;
    private List<ValidationError> errors;
    private List<ValidationWarning> warnings;
    
    @Data
    @Builder
    public static class ValidationError {
        private String code;
        private String field;
        private String message;
    }
    
    @Data
    @Builder
    public static class ValidationWarning {
        private String code;
        private String message;
    }
}
