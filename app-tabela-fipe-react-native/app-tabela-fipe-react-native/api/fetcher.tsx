import axios from 'axios'

const fetcher = (url) => {

    return axios.get(url)
        .then((resp) => {

            return resp.data
        })
}

export default fetcher;