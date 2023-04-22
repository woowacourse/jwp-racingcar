package racing.web.controller.dto.request.validator;

public interface RequestValidator<T> {

    void validate(T requestDto);
}
