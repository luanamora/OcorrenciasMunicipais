using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TipoOcorrencia
    {
        public TipoOcorrencia()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        public int CdTipoocorrencia { get; set; }
        public string DsTipoocorrencia { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
