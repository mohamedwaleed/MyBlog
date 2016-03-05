package my.blog.common.services;

import com.google.common.collect.Lists;
import my.blog.common.PageViewModel;
import my.blog.common.commands.CommonResponseCommand;
import my.blog.common.commands.ListCommonResponse;
import my.blog.common.commands.PagedCommonResponse;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class MappingService {

    @Autowired
    private DozerBeanMapper mapper;


    public <T> T map(Object source, Class<T> target) {
        return mapper.map(source, target);
    }

    public <T> void map(Object source, T target) {
        mapper.map(source, target);
    }


    public <T, O> List<O> map(List<T> objects, Class<O> target, List<O> destination) {
        return (List<O>) internalMap(objects, target, destination);
    }

    public <T, O> Set<O> map(Set<T> objects, Class<O> target, Set<O> destination) {
        return (Set<O>) internalMap(objects, target, destination);
    }

    public <T, O> List<O> map(List<T> objects, Class<O> target) {
        return (List<O>) internalMap(objects, target, (List<O>) Lists.newArrayList());
    }


    private <T, O> Collection<O> internalMap(Collection<T> objects, Class<O> target, Collection<O> destination) {
        for (T t : objects) {
            destination.add(mapper.map(t, target));
        }
        return destination;
    }

    public <D> PagedCommonResponse<D> createPagedCommonResponse(PageViewModel<D> pageViewModel, Class<D> dtoType) {
        return new PagedCommonResponse<D>(pageViewModel);
    }

    public <D> PagedCommonResponse<D> createPagedCommonResponse(Page<D> page) {
        PageViewModel<D> pageViewModel = new PageViewModel<>(page);
        return new PagedCommonResponse<D>(pageViewModel);
    }

    public <D> CommonResponseCommand<D> createCommonResponse(D result) {
        return new CommonResponseCommand<D>(result);
    }

    public <D> ListCommonResponse<D> createListCommonResponse(List<D> list, Class<D> dtoType) {
        return new ListCommonResponse<>(list);
    }

    public <E, D> PageViewModel<D> mapPage(Page<E> page, Class<D> dtoType) {
        List<D> content = map(page.getContent(), dtoType);
        PageViewModel<D> resultPage = map(page, PageViewModel.class);
        resultPage.setPageContent(content);
        return resultPage;
    }


}
