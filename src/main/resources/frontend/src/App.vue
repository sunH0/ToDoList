<template>
  <h1>ToDo App</h1>
  <form @submit.prevent="handler.addTodo">
    <label>New ToDo </label>
    <input
        v-model="content"
        autocomplete="off"
        name="newTodo"
    >
    <button>Add ToDo</button>
  </form>
  <h2>ToDo List</h2>
  <ul>
    <li v-for="todo in todos"
        :key="todo.id">
      <form v-if="todo.isEdit" style="flex-basis: 85%" @submit.prevent="handler.updateTodo(todo.id)">
        <input v-model="todo.content" style="height: inherit">
      </form>
      <div v-else
           style="flex-basis: 85%"
           @click="handler.changeStatus(todo.id, todo.isComplete() ? 'ACTIVE' : 'COMPLETE')">
        <p :class="{ done: todo.isComplete() }">
          {{ todo.content }}
        </p>
        <p :class="{ done: todo.isComplete() }">
          {{ dayjs(todo.updatedAt).format('YYYY-MM-DD') }}
        </p>
      </div>
      <button @click="todo.isEdit = true">수정</button>
      <button @click="handler.deleteTodo(todo.id)">삭제</button>
    </li>
    <h3 v-if="todos.length === 0">Empty list.</h3>
  </ul>
  <div class="status">
    <span @click="handler.fetchTodos()">
      전체
    </span>
    <span @click="handler.fetchTodos('ACTIVE')">
      진행중
    </span>
    <span @click="handler.fetchTodos('COMPLETE')">
      완료
    </span>
  </div>
</template>

<script>
import {changeStatus, createTodo, deleteTodo, fetchTodos, updateTodo} from "@/api/todo";
import {onMounted, reactive, toRefs} from 'vue'
import dayjs from 'dayjs'

export default {
  name: 'App',
  setup() {
    const state = reactive({
      content: '',
      todos: [],
    })

    const handler = {
      addTodo: async () => {
        // AddTodoRequest로 들어올 fields : content, status
        const params = {content: state.content, status: 'active'}
        // todo는 createTodo(request)의 결과값
        const todo = await createTodo(params)
        // view의 state.todos에 todo를 넣어라?
        state.todos.push(todo)
        // view의 state.content는 비워라
        state.content = ''
      },
      fetchTodos: async (status = '') => {
        // todos는 fetchTodos(status)의 결과값
        const todos = await fetchTodos(status);
        // view의 state.todos는 todos (fetchTodos(status)의 return값은 list?)
        state.todos = (todos);
      },
      updateTodo: async (id) => {
        const index = state.todos.findIndex(todo => todo.id === id)
        const todo = state.todos[index]

        // request로 들어올 params
        const params = {
          content: todo.content
        }
        // updateTodo(id, params) 요청
        await updateTodo(id, params);
        // form창이 닫힌다는건가?
        todo.isEdit = false;
      },
      deleteTodo: async (id) => {
        // deleteTodo(id) 요청
        await deleteTodo(id);
        // delete하는 todo의 index를 찾아서 state에서 삭제
        const index = state.todos.findIndex(todo => todo.id === id)
        state.todos.splice(index, 1)
      },
      changeStatus: async (id, status) => {
        // changeStatus(id, status) 요청
        await changeStatus(id, status);
        // change하는 todo의 index를 찾아서 state에서 todo의 status 변경
        const index = state.todos.findIndex(todo => todo.id === id)
        state.todos[index].status = status;
      }
    }

    onMounted(async () => {
      // todos 조회
      await handler.fetchTodos();
    })

    return {
      ...toRefs(state),
      handler,
      dayjs,
    }
  }
}
</script>

<style lang="scss">
$border: 2px solid rgba(
    $color: white,
    $alpha: 0.35,
);
$size1: 6px;
$size2: 12px;
$size3: 18px;
$size4: 24px;
$size5: 48px;
$backgroundColor: #27292d;
$textColor: white;
$primaryColor: #a0a4d9;
$secondTextColor: #1f2023;
body {
  margin: 0;
  padding: 0;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: $backgroundColor;
  color: $textColor;

  #app {
    max-width: 600px;
    margin-left: auto;
    margin-right: auto;
    padding: 20px;

    h1 {
      font-weight: bold;
      font-size: 28px;
      text-align: center;
    }

    form {
      display: flex;
      flex-direction: column;
      width: 100%;

      label {
        font-size: 14px;
        font-weight: bold;
      }

      input,
      button {
        height: $size5;
        box-shadow: none;
        outline: none;
        padding-left: $size2;
        padding-right: $size2;
        border-radius: $size1;
        font-size: 18px;
        margin-top: $size1;
        margin-bottom: $size2;
      }

      input {
        background-color: transparent;
        border: $border;
        color: inherit;
      }
    }

    button {
      cursor: pointer;
      background-color: $primaryColor;
      border: 1px solid $primaryColor;
      color: $secondTextColor;
      font-weight: bold;
      outline: none;
      border-radius: $size1;
    }

    h2 {
      font-size: 22px;
      border-bottom: $border;
      padding-bottom: $size1;
    }

    ul {
      padding: 10px;

      li {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: $border;
        padding: $size2 $size4;
        border-radius: $size1;
        margin-bottom: $size2;

        span {
          cursor: pointer;
        }

        .done {
          text-decoration: line-through;
        }

        button {
          font-size: $size2;
          padding: $size1;
        }
      }
    }

    h4 {
      text-align: center;
      opacity: 0.5;
      margin: 0;
    }
  }

  .status {
    display: flex;
    justify-content: space-between;

    span {
      cursor: pointer;
      width: 100%;
      margin: 10px;
      text-align: center;
      background-color: $primaryColor;
      border: 1px solid $primaryColor;
      color: $secondTextColor;
      font-weight: bold;
      outline: none;
      border-radius: $size1;
    }
  }
}
</style>
